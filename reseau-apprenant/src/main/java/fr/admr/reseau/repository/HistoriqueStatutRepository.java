package fr.admr.reseau.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.admr.reseau.domain.HistoriqueStatut;
import fr.admr.reseau.domain.Participant;

public interface HistoriqueStatutRepository extends CrudRepository<HistoriqueStatut,Long> {

	public List<HistoriqueStatut> findByParticipant(Participant participant);
}
