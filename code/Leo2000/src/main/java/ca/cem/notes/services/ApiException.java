package ca.cem.notes.services;

import org.springframework.http.HttpStatus;

// Une erreur prevue par le contrat : un code HTTP et un message lisible.
public class ApiException extends RuntimeException {
    public final HttpStatus statut;

    public ApiException(HttpStatus statut, String message) {
        super(message);
        this.statut = statut;
    }

    public static ApiException invalide(String m) { return new ApiException(HttpStatus.BAD_REQUEST, m); }
    public static ApiException nonConnecte() { return new ApiException(HttpStatus.UNAUTHORIZED, "Connexion requise"); }
    public static ApiException interdit(String m) { return new ApiException(HttpStatus.FORBIDDEN, m); }
    public static ApiException introuvable(String m) { return new ApiException(HttpStatus.NOT_FOUND, m); }
    public static ApiException conflit(String m) { return new ApiException(HttpStatus.CONFLICT, m); }
}
