package ca.cem.notes.depots;

import ca.cem.notes.modele.Evaluation;
import ca.cem.notes.modele.Note;
import ca.cem.notes.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepotNotes extends JpaRepository<Note, Long> {
    Optional<Note> findByEvaluationAndEtudiant(Evaluation evaluation, Utilisateur etudiant);
}
