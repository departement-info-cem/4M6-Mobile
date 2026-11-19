package ca.cem.notes.depots;

import ca.cem.notes.modele.Evaluation;
import ca.cem.notes.modele.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotEvaluations extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByGroupeOrderByDateAsc(Groupe groupe);
}
