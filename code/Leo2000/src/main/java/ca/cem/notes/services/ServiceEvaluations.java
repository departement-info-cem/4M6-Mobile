package ca.cem.notes.services;

import ca.cem.notes.depots.DepotEvaluations;
import ca.cem.notes.depots.DepotNotes;
import ca.cem.notes.depots.DepotUtilisateurs;
import ca.cem.notes.dto.Dto.EvaluationRequete;
import ca.cem.notes.modele.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceEvaluations {
    private final DepotEvaluations depot;
    private final DepotNotes depotNotes;
    private final DepotUtilisateurs depotUtilisateurs;
    private final ServiceGroupes groupes;

    public ServiceEvaluations(DepotEvaluations depot, DepotNotes depotNotes,
                              DepotUtilisateurs depotUtilisateurs, ServiceGroupes groupes) {
        this.depot = depot;
        this.depotNotes = depotNotes;
        this.depotUtilisateurs = depotUtilisateurs;
        this.groupes = groupes;
    }

    public List<Evaluation> evaluations(Groupe g) {
        return depot.findByGroupeOrderByDateAsc(g);
    }

    public Evaluation creer(Groupe g, EvaluationRequete r) {
        if (ServiceAuth.vide(r.titre()) || r.ponderation() == null || r.ponderation() <= 0)
            throw ApiException.invalide("Titre et ponderation (> 0) obligatoires");
        double total = evaluations(g).stream().mapToDouble(e -> e.ponderation).sum() + r.ponderation();
        if (total > 100.0001) throw ApiException.invalide("Les ponderations du groupe depassent 100 %");
        Evaluation e = new Evaluation();
        e.titre = r.titre();
        e.ponderation = r.ponderation();
        try {
            e.date = r.date() == null ? LocalDate.now() : LocalDate.parse(r.date());
        } catch (DateTimeParseException ex) {
            throw ApiException.invalide("Date au format yyyy-MM-dd");
        }
        e.groupe = g;
        return depot.save(e);
    }

    public Evaluation evaluationDuEnseignant(Long idEvaluation, Utilisateur u) {
        Evaluation e = depot.findById(idEvaluation).orElseThrow(() -> ApiException.introuvable("Evaluation inexistante"));
        groupes.duEnseignant(e.groupe.id, u);
        return e;
    }

    // Saisie d'une note : reservee aux enseignants
    public Evaluation evaluationPourSaisie(Long idEvaluation, Utilisateur u) {
        if (u.role != Role.ENSEIGNANT) throw ApiException.interdit("Reserve aux enseignants");
        return depot.findById(idEvaluation).orElseThrow(() -> ApiException.introuvable("Evaluation inexistante"));
    }

    // Une note par etudiant inscrit; creee vide (valeur null) au premier besoin
    public Note noteDe(Evaluation e, Utilisateur etudiant) {
        return depotNotes.findByEvaluationAndEtudiant(e, etudiant).orElseGet(() -> {
            Note n = new Note();
            n.evaluation = e;
            n.etudiant = etudiant;
            return depotNotes.save(n);
        });
    }

    public List<Note> notes(Evaluation e) {
        return e.groupe.etudiants.stream()
                .sorted(Comparator.comparing(u -> u.nom))
                .map(u -> noteDe(e, u))
                .toList();
    }

    public Note saisir(Evaluation e, Long idEtudiant, Double valeur) {
        if (valeur == null || valeur < 0 || valeur > 100) throw ApiException.invalide("Une note vaut de 0 a 100");
        Utilisateur etudiant = depotUtilisateurs.findById(idEtudiant)
                .filter(e.groupe::inscrit)
                .orElseThrow(() -> ApiException.introuvable("Etudiant pas inscrit dans ce groupe"));
        Note n = noteDe(e, etudiant);
        n.valeur = valeur;
        return depotNotes.save(n);
    }

    public List<Note> notesDeLEtudiant(Groupe g, Utilisateur etudiant) {
        return evaluations(g).stream().map(e -> noteDe(e, etudiant)).toList();
    }

    // Moyenne ponderee sur les notes saisies seulement; null si aucune
    public static Double moyenne(List<Note> notes) {
        double somme = 0, ponderations = 0;
        for (Note n : notes) {
            if (n.valeur == null) continue;
            somme += n.valeur * n.evaluation.ponderation;
            ponderations += n.evaluation.ponderation;
        }
        return ponderations == 0 ? null : somme / ponderations;
    }
}
