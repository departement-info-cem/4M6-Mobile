package ca.cem.notes.services;

import ca.cem.notes.depots.DepotUtilisateurs;
import ca.cem.notes.dto.Dto.InscriptionRequete;
import ca.cem.notes.modele.Role;
import ca.cem.notes.modele.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuth {
    private final DepotUtilisateurs depot;
    private final BCryptPasswordEncoder encodeur = new BCryptPasswordEncoder();

    public ServiceAuth(DepotUtilisateurs depot) {
        this.depot = depot;
    }

    public Utilisateur inscrire(InscriptionRequete r) {
        if (vide(r.courriel()) || vide(r.motDePasse()) || vide(r.nom()) || vide(r.matricule()))
            throw ApiException.invalide("Courriel, mot de passe, nom et matricule sont obligatoires");
        if (r.motDePasse().length() < 8) throw ApiException.invalide("Mot de passe trop court (8 caracteres minimum)");
        if (!r.matricule().matches("\\d{7}")) throw ApiException.invalide("Le matricule a 7 chiffres");
        if (depot.findByCourriel(r.courriel()).isPresent()) throw ApiException.conflit("Courriel deja utilise");
        if (depot.findByMatricule(r.matricule()).isPresent()) throw ApiException.conflit("Matricule deja utilise");
        return depot.save(nouveau(r.nom(), r.courriel(), r.motDePasse(), Role.ETUDIANT, r.matricule()));
    }

    public Utilisateur verifier(String courriel, String motDePasse) {
        // Meme message que le courriel existe ou non : on ne revele pas les comptes existants
        return depot.findByCourriel(courriel == null ? "" : courriel)
                .filter(u -> motDePasse != null && encodeur.matches(motDePasse, u.motDePasse))
                .orElseThrow(() -> new ApiException(org.springframework.http.HttpStatus.UNAUTHORIZED,
                        "Courriel ou mot de passe invalide"));
    }

    public Utilisateur nouveau(String nom, String courriel, String motDePasse, Role role, String matricule) {
        Utilisateur u = new Utilisateur();
        u.nom = nom;
        u.courriel = courriel;
        u.motDePasse = encodeur.encode(motDePasse);
        u.role = role;
        u.matricule = matricule;
        return u;
    }

    static boolean vide(String s) {
        return s == null || s.isBlank();
    }
}
