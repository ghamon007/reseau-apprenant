package fr.admr.reseau.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.TypeFormation;
import fr.admr.reseau.repository.FormationRepository;
import fr.admr.reseau.repository.ParticipantRepository;

@ComponentScan(basePackages = "fr.admr.reseau")
@EntityScan("fr.admr.reseau.domain")
@EnableJpaRepositories("fr.admr.reseau.repository")
@SpringBootApplication
public class ReseauApprenantApp 
{
	private static final Logger log = LoggerFactory.getLogger(ReseauApprenantApp.class);

    public static void main( String[] args )
    {
        SpringApplication.run(ReseauApprenantApp.class, args);
    }
    
    
    @Bean
    public CommandLineRunner loadData(ParticipantRepository participantRepository, FormationRepository formationRepository) {
        return args -> {
        	// save a couple of Participants
        	Participant tuteur = new Participant("Jack", "Bauer");
			participantRepository.save(tuteur);
			Participant formateur = new Participant("Chloe", "O'Brian");
			participantRepository.save(formateur);
			Participant participant1 = new Participant("Kim", "Bauer");
			participantRepository.save(participant1);
			Participant participant2 = new Participant("David", "Palmer");
			participantRepository.save(participant2);
			Participant formateur2 = new Participant("Michelle", "Dessler");
			participantRepository.save(formateur2);

			// save formation
			
			Date dateFormation1 = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/2017");
			formationRepository.save(new Formation(dateFormation1,TypeFormation.TRANSMISSION_REFERENTIEL,tuteur,formateur,participant1,participant2));
			
			
			// fetch all Participants
			log.info("Participants found with findAll():");
			log.info("-------------------------------");
			for (Participant participant : participantRepository.findAll()) {
				log.info(participant.toString());
			}
			log.info("");

			// fetch an individual Participant by ID
			Participant participant = participantRepository.findOne(1L);
			log.info("Participant found with findOne(1L):");
			log.info("--------------------------------");
			log.info(participant.toString());
			log.info("");

			// fetch Participants by last name
			log.info("Participant found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Participant bauer : participantRepository
					.findByNomStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			
			// fetch Formations by Date and Type
			log.info("Formation found with findByDateFormationAndTypeFormationEquals(Date):");
			log.info("--------------------------------------------");
			for (Formation formation : formationRepository
					.findByDateFormationAndTypeFormationEquals(dateFormation1, TypeFormation.TRANSMISSION_REFERENTIEL)) {
				log.info(formation.toString());
			}

        };
    }

}
