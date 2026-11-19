package ca.cem.notes.services;

import ca.cem.notes.depots.DepotGroupes;
import ca.cem.notes.depots.DepotUtilisateurs;
import ca.cem.notes.dto.Dto.GroupeRequete;
import ca.cem.notes.modele.Groupe;
import ca.cem.notes.modele.Role;
import ca.cem.notes.modele.Utilisateur;
import ca.cem.notes.dto.Dto.UtilisateurDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceGroupes {
    private final DepotGroupes depot;
    private final DepotUtilisateurs depotUtilisateurs;
    private final JdbcTemplate jdbc;

    public ServiceGroupes(DepotGroupes depot, DepotUtilisateurs depotUtilisateurs, JdbcTemplate jdbc) {
        this.depot = depot;
        this.depotUtilisateurs = depotUtilisateurs;
        this.jdbc = jdbc;
    }

    public List<Groupe> groupesDe(Utilisateur u) {
        return u.role == Role.ENSEIGNANT ? depot.findByEnseignant(u) : depot.findByEtudiantsContaining(u);
    }

    public Groupe creer(Utilisateur enseignant, GroupeRequete r) {
        if (ServiceAuth.vide(r.cours()) || ServiceAuth.vide(r.numero()) || ServiceAuth.vide(r.session()))
            throw ApiException.invalide("Cours, numero et session sont obligatoires");
        Groupe g = new Groupe();
        g.cours = r.cours();
        g.numero = r.numero();
        g.session = r.session();
        g.enseignant = enseignant;
        return depot.save(g);
    }

    // Regle d'acces : seul l'enseignant du groupe
    public Groupe duEnseignant(Long idGroupe, Utilisateur u) {
        Groupe g = depot.findById(idGroupe).orElseThrow(() -> ApiException.introuvable("Groupe inexistant"));
        if (u.role != Role.ENSEIGNANT || !g.enseignant.id.equals(u.id))
            throw ApiException.interdit("Reserve a l'enseignant du groupe");
        return g;
    }

    // Regle d'acces : l'enseignant du groupe ou un etudiant inscrit
    public Groupe lisiblePar(Long idGroupe, Utilisateur u) {
        Groupe g = depot.findById(idGroupe).orElseThrow(() -> ApiException.introuvable("Groupe inexistant"));
        boolean enseignant = u.role == Role.ENSEIGNANT && g.enseignant.id.equals(u.id);
        boolean etudiant = u.role == Role.ETUDIANT && g.inscrit(u);
        if (!enseignant && !etudiant) throw ApiException.interdit("Pas dans ce groupe");
        return g;
    }

    @Transactional
    public void inscrire(Groupe g, String matricule) {
        Utilisateur e = depotUtilisateurs.findByMatricule(matricule == null ? "" : matricule)
                .filter(u -> u.role == Role.ETUDIANT)
                .orElseThrow(() -> ApiException.introuvable("Matricule inconnu"));
        if (g.inscrit(e)) throw ApiException.conflit("Etudiant deja inscrit");
        g.etudiants.add(e);
        depot.save(g);
    }

    // Recherche d'etudiants par nom (partie du nom, sans tenir compte de la casse)
    public List<UtilisateurDTO> recherche(String nom) {
        String sql = "SELECT id, nom, courriel, role, matricule FROM utilisateur "
                + "WHERE role = 'ETUDIANT' AND nom ILIKE '%" + (nom == null ? "" : nom) + "%' ORDER BY nom";
        return jdbc.query(sql, (rs, i) -> new UtilisateurDTO(rs.getLong(1), rs.getString(2), rs.getString(3),
                Role.valueOf(rs.getString(4)), rs.getString(5)));
    }
}
