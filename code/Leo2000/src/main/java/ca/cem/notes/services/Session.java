package ca.cem.notes.services;

import ca.cem.notes.depots.DepotUtilisateurs;
import ca.cem.notes.modele.Role;
import ca.cem.notes.modele.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

// L'identite vient TOUJOURS d'ici (le cookie de session), jamais d'un parametre envoye par le client.
// Composant singleton : la requete injectee est un proxy vers la requete HTTP en cours.
@Component
public class Session {
    private static final String CLE = "idUtilisateur";
    private final HttpServletRequest requete;
    private final DepotUtilisateurs depot;

    public Session(HttpServletRequest requete, DepotUtilisateurs depot) {
        this.requete = requete;
        this.depot = depot;
    }

    public Utilisateur utilisateur() {
        HttpSession s = requete.getSession(false);
        if (s == null) return null;
        Long id = (Long) s.getAttribute(CLE);
        return id == null ? null : depot.findById(id).orElse(null);
    }

    public Utilisateur exige() {
        Utilisateur u = utilisateur();
        if (u == null) throw ApiException.nonConnecte();
        return u;
    }

    public Utilisateur exige(Role role) {
        Utilisateur u = exige();
        if (u.role != role) throw ApiException.interdit("Reserve au role " + role);
        return u;
    }

    public void connecter(Utilisateur u) {
        HttpSession ancienne = requete.getSession(false);
        if (ancienne != null) ancienne.invalidate();   // nouvel identifiant de session a chaque connexion
        requete.getSession(true).setAttribute(CLE, u.id);
    }

    public void deconnecter() {
        HttpSession s = requete.getSession(false);
        if (s != null) s.invalidate();
    }
}
