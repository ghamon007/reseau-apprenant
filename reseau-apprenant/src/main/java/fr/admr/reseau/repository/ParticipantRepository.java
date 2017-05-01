package fr.admr.reseau.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.admr.reseau.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	
	List<Participant> findByNomStartsWithIgnoreCase(String unNom);

}