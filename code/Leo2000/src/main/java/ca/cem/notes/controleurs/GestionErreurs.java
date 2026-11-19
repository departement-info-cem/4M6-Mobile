package ca.cem.notes.controleurs;

import ca.cem.notes.dto.Dto.ErreurDTO;
import ca.cem.notes.services.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// Transforme les exceptions en reponses du contrat : code HTTP + {"message": ...}
@RestControllerAdvice
public class GestionErreurs {
    private static final Logger log = LoggerFactory.getLogger(GestionErreurs.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErreurDTO> api(ApiException e) {
        log.info("{} : {}", e.statut.value(), e.getMessage());
        return ResponseEntity.status(e.statut).body(new ErreurDTO(e.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErreurDTO> illisible(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErreurDTO("Requete invalide"));
    }
}
