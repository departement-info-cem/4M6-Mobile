package ca.cem.notes.dto;

import ca.cem.notes.modele.*;

import java.util.List;

// Tous les DTO de l'API du TP1. Un record Java = une classe de donnees immuable.
public final class Dto {
    private Dto() {}

    public record UtilisateurDTO(Long id, String nom, String courriel, Role role, String matricule) {
        public static UtilisateurDTO de(Utilisateur u) {
            return new UtilisateurDTO(u.id, u.nom, u.courriel, u.role, u.matricule);
        }
    }

    public record GroupeDTO(Long id, String cours, String numero, String session, String enseignant) {
        public static GroupeDTO de(Groupe g) {
            return new GroupeDTO(g.id, g.cours, g.numero, g.session, g.enseignant.nom);
        }
    }

    public record EvaluationDTO(Long id, String titre, Double ponderation, String date) {
        public static EvaluationDTO de(Evaluation e) {
            return new EvaluationDTO(e.id, e.titre, e.ponderation, e.date.toString());
        }
    }

    public record NoteDTO(Long id, Long idEvaluation, String evaluation, Double ponderation,
                          Long idEtudiant, String nomEtudiant, Double valeur) {
        public static NoteDTO de(Note n) {
            return new NoteDTO(n.id, n.evaluation.id, n.evaluation.titre, n.evaluation.ponderation,
                    n.etudiant.id, n.etudiant.nom, n.valeur);
        }
    }

    public record ReleveDTO(GroupeDTO groupe, List<NoteDTO> notes, Double moyenne) {}

    public record ErreurDTO(String message) {}

    // Corps des requetes
    public record InscriptionRequete(String courriel, String motDePasse, String nom, String matricule) {}
    public record ConnexionRequete(String courriel, String motDePasse) {}
    public record GroupeRequete(String cours, String numero, String session) {}
    public record InscriptionGroupeRequete(String matricule) {}
    public record EvaluationRequete(String titre, Double ponderation, String date) {}
    public record NoteRequete(Double valeur) {}
}
