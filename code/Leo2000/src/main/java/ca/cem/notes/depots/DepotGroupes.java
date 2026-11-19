package ca.cem.notes.depots;

import ca.cem.notes.modele.Groupe;
import ca.cem.notes.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotGroupes extends JpaRepository<Groupe, Long> {
    List<Groupe> findByEnseignant(Utilisateur enseignant);
    List<Groupe> findByEtudiantsContaining(Utilisateur etudiant);
}
