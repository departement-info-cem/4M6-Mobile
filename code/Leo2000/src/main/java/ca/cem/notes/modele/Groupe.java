package ca.cem.notes.modele;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groupe {
    @Id @GeneratedValue public Long id;
    public String cours;
    public String numero;
    public String session;
    @ManyToOne public Utilisateur enseignant;
    @ManyToMany(fetch = FetchType.EAGER) public Set<Utilisateur> etudiants = new HashSet<>();

    public boolean inscrit(Utilisateur u) {
        return etudiants.stream().anyMatch(e -> e.id.equals(u.id));
    }
}
