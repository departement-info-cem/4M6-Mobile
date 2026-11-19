package ca.cem.notes.depots;

import ca.cem.notes.modele.Role;
import ca.cem.notes.modele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepotUtilisateurs extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByCourriel(String courriel);
    Optional<Utilisateur> findByMatricule(String matricule);
    // Requete derivee : Spring genere une requete parametree, pas d'injection SQL possible
    List<Utilisateur> findByRoleAndNomContainingIgnoreCase(Role role, String nom);
}
