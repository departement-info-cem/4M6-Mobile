package ca.cem.notes.controleurs;

import ca.cem.notes.dto.Dto.*;
import ca.cem.notes.modele.Utilisateur;
import ca.cem.notes.services.ServiceAuth;
import ca.cem.notes.services.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControleurAuth {
    private final ServiceAuth auth;
    private final Session session;

    public ControleurAuth(ServiceAuth auth, Session session) {
        this.auth = auth;
        this.session = session;
    }

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public UtilisateurDTO inscription(@RequestBody InscriptionRequete r) {
        return UtilisateurDTO.de(auth.inscrire(r));
    }

    @PostMapping("/connexion")
    public UtilisateurDTO connexion(@RequestBody ConnexionRequete r) {
        Utilisateur u = auth.verifier(r.courriel(), r.motDePasse());
        session.connecter(u);
        return UtilisateurDTO.de(u);
    }

    @PostMapping("/deconnexion")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deconnexion() {
        session.exige();
        session.deconnecter();
    }

    @GetMapping("/moi")
    public UtilisateurDTO moi() {
        return UtilisateurDTO.de(session.exige());
    }
}
