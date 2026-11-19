package ca.cem.notes.controleurs;

import ca.cem.notes.dto.Dto.*;
import ca.cem.notes.modele.Evaluation;
import ca.cem.notes.modele.Utilisateur;
import ca.cem.notes.services.ServiceEvaluations;
import ca.cem.notes.services.ServiceGroupes;
import ca.cem.notes.services.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControleurEvaluations {
    private final Session session;
    private final ServiceGroupes groupes;
    private final ServiceEvaluations evaluations;

    public ControleurEvaluations(Session session, ServiceGroupes groupes, ServiceEvaluations evaluations) {
        this.session = session;
        this.groupes = groupes;
        this.evaluations = evaluations;
    }

    @GetMapping("/groupes/{id}/evaluations")
    public List<EvaluationDTO> evaluations(@PathVariable Long id) {
        return evaluations.evaluations(groupes.lisiblePar(id, session.exige())).stream().map(EvaluationDTO::de).toList();
    }

    @PostMapping("/groupes/{id}/evaluations")
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluationDTO creer(@PathVariable Long id, @RequestBody EvaluationRequete r) {
        return EvaluationDTO.de(evaluations.creer(groupes.duEnseignant(id, session.exige()), r));
    }

    @GetMapping("/evaluations/{id}/notes")
    public List<NoteDTO> notes(@PathVariable Long id) {
        Evaluation e = evaluations.evaluationDuEnseignant(id, session.exige());
        return evaluations.notes(e).stream().map(NoteDTO::de).toList();
    }

    @PutMapping("/evaluations/{id}/notes/{idEtudiant}")
    public NoteDTO saisir(@PathVariable Long id, @PathVariable Long idEtudiant, @RequestBody NoteRequete r) {
        Utilisateur u = session.exige();
        return NoteDTO.de(evaluations.saisir(evaluations.evaluationPourSaisie(id, u), idEtudiant, r.valeur()));
    }
}
