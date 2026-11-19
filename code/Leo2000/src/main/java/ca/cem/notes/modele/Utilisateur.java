package ca.cem.notes.modele;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id @GeneratedValue public Long id;
    public String nom;
    @Column(unique = true) public String courriel;
    public String motDePasse;          // hachage BCrypt, jamais le mot de passe
    @Enumerated(EnumType.STRING) public Role role;
    @Column(unique = true) public String matricule;   // null pour un enseignant
}
