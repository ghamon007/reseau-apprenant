package fr.admr.reseau.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.domain.TypeFormation;

public interface FormationRepository extends JpaRepository<Formation, Long> {
	
	List<Formation> findByDateFormationAndTypeFormationEquals(Date uneDateFormation, TypeFormation unTypeFormation);

}
