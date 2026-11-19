package ca.cem.notes.modele;

import jakarta.persistence.*;

@Entity
public class Note {
    @Id @GeneratedValue public Long id;
    @ManyToOne public Evaluation evaluation;
    @ManyToOne public Utilisateur etudiant;
    public Double valeur;   // null = pas encore saisie
}
