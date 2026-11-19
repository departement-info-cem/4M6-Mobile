package ca.cem.notes.controleurs;

import ca.cem.notes.dto.Dto.*;
import ca.cem.notes.modele.*;
import ca.cem.notes.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ControleurGroupes {
    private final Session session;
    private final ServiceGroupes groupes;
    private final ServiceEvaluations evaluations;

    public ControleurGroupes(Session session, ServiceGroupes groupes, ServiceEvaluations evaluations) {
        this.session = session;
        this.groupes = groupes;
        this.evaluations = evaluations;
    }

    @GetMapping("/groupes")
    public List<GroupeDTO> groupes() {
        return groupes.groupesDe(session.exige()).stream().map(GroupeDTO::de).toList();
    }

    @PostMapping("/groupes")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupeDTO creer(@RequestBody GroupeRequete r) {
        return GroupeDTO.de(groupes.creer(session.exige(Role.ENSEIGNANT), r));
    }

    @GetMapping("/groupes/{id}/etudiants")
    public List<UtilisateurDTO> etudiants(@PathVariable Long id) {
        Groupe g = groupes.duEnseignant(id, session.exige());
        return g.etudiants.stream().sorted(Comparator.comparing(u -> u.nom)).map(UtilisateurDTO::de).toList();
    }

    @PostMapping("/groupes/{id}/etudiants")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inscrire(@PathVariable Long id, @RequestBody InscriptionGroupeRequete r) {
        groupes.inscrire(groupes.duEnseignant(id, session.exige()), r.matricule());
    }

    @GetMapping("/etudiants")
    public List<UtilisateurDTO> recherche(@RequestParam(defaultValue = "") String nom) {
        session.exige(Role.ENSEIGNANT);
        return groupes.recherche(nom);
    }

    @GetMapping("/groupes/{id}/mes-notes")
    public ReleveDTO mesNotes(@PathVariable Long id) {
        Utilisateur etudiant = session.exige(Role.ETUDIANT);
        Groupe g = groupes.lisiblePar(id, etudiant);
        List<Note> notes = evaluations.notesDeLEtudiant(g, etudiant);
        return new ReleveDTO(GroupeDTO.de(g), notes.stream().map(NoteDTO::de).toList(), ServiceEvaluations.moyenne(notes));
    }
}
