package ca.cem.notes;

import ca.cem.notes.depots.DepotEvaluations;
import ca.cem.notes.depots.DepotGroupes;
import ca.cem.notes.depots.DepotUtilisateurs;
import ca.cem.notes.modele.*;
import ca.cem.notes.services.ServiceAuth;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Comptes et groupes de API.md, crees au premier demarrage (BD vide)
@Component
public class DonneesInitiales implements CommandLineRunner {
    private final DepotUtilisateurs utilisateurs;
    private final DepotGroupes groupes;
    private final DepotEvaluations evaluations;
    private final ServiceAuth auth;

    public DonneesInitiales(DepotUtilisateurs utilisateurs, DepotGroupes groupes,
                            DepotEvaluations evaluations, ServiceAuth auth) {
        this.utilisateurs = utilisateurs;
        this.groupes = groupes;
        this.evaluations = evaluations;
        this.auth = auth;
    }

    @Override
    public void run(String... args) {
        if (utilisateurs.count() > 0) return;
        Utilisateur ada = utilisateurs.save(auth.nouveau("Ada", "ada@cem.ca", "Passw0rd!", Role.ENSEIGNANT, null));
        Utilisateur blaise = utilisateurs.save(auth.nouveau("Blaise", "blaise@cem.ca", "Passw0rd!", Role.ENSEIGNANT, null));
        Utilisateur chloe = utilisateurs.save(auth.nouveau("Chloé", "chloe@cem.ca", "Passw0rd!", Role.ETUDIANT, "2027001"));
        Utilisateur dany = utilisateurs.save(auth.nouveau("Dany", "dany@cem.ca", "Passw0rd!", Role.ETUDIANT, "2027002"));
        Groupe g = new Groupe();
        g.cours = "420-4M6-EM";
        g.numero = "00001";
        g.session = "H27";
        g.enseignant = ada;
        g.etudiants.add(chloe);
        g.etudiants.add(dany);
        groupes.save(g);
        evaluation(g, "TP1", 10.0, LocalDate.of(2027, 2, 15));
        evaluation(g, "Intra", 15.0, LocalDate.of(2027, 3, 10));

        // Un groupe de Blaise ou Chloe est aussi inscrite
        Groupe b = new Groupe();
        b.cours = "420-4N6-EM";
        b.numero = "00002";
        b.session = "H27";
        b.enseignant = blaise;
        b.etudiants.add(chloe);
        groupes.save(b);
        evaluation(b, "Examen", 40.0, LocalDate.of(2027, 4, 20));
    }

    private void evaluation(Groupe g, String titre, Double ponderation, LocalDate date) {
        Evaluation e = new Evaluation();
        e.titre = titre;
        e.ponderation = ponderation;
        e.date = date;
        e.groupe = g;
        evaluations.save(e);
    }
}
