package fr.admr.reseau.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.admr.reseau.domain.HistoriqueStatut;
import fr.admr.reseau.domain.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	
	List<Participant> findByNomStartsWithIgnoreCase(String unNom);
	
	List<Participant> findByHistoriqueStatuts(List<HistoriqueStatut> historiqueStatuts);

}
