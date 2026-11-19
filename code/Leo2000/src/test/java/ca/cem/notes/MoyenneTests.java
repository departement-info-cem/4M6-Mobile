package ca.cem.notes;

import ca.cem.notes.modele.Evaluation;
import ca.cem.notes.modele.Note;
import ca.cem.notes.services.ServiceEvaluations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoyenneTests {

    static Note note(Double valeur, double ponderation) {
        Evaluation e = new Evaluation();
        e.ponderation = ponderation;
        Note n = new Note();
        n.evaluation = e;
        n.valeur = valeur;
        return n;
    }

    @Test
    void ponderationsDifferentes() {
        // (80 x 10 + 60 x 30) / 40 = 65 ; une moyenne simple donnerait 70
        assertEquals(65.0, ServiceEvaluations.moyenne(List.of(note(80.0, 10), note(60.0, 30))), 1e-9);
    }

    @Test
    void notesNonSaisiesIgnorees() {
        assertEquals(80.0, ServiceEvaluations.moyenne(List.of(note(80.0, 10), note(null, 30))), 1e-9);
    }

    @Test
    void aucuneNoteSaisie() {
        assertNull(ServiceEvaluations.moyenne(List.of(note(null, 10))));
        assertNull(ServiceEvaluations.moyenne(List.of()));
    }
}
