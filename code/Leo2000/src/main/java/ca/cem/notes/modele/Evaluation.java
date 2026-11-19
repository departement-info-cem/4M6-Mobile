package ca.cem.notes.modele;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Evaluation {
    @Id @GeneratedValue public Long id;
    public String titre;
    public Double ponderation;
    public LocalDate date;
    @ManyToOne public Groupe groupe;
}
