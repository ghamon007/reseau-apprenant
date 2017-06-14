package fr.admr.reseau.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import fr.admr.reseau.domain.HistoriqueStatut;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.domain.TypeFormation;
import fr.admr.reseau.repository.FormationRepository;
import fr.admr.reseau.repository.HistoriqueStatutRepository;
import fr.admr.reseau.repository.ParticipantRepository;
import fr.admr.reseau.service.ParticipantService;
import fr.admr.reseau.service.ParticipantServiceImpl;

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
    public CommandLineRunner loadData(ParticipantService participantService, FormationRepository formationRepository, HistoriqueStatutRepository historiqueStatutRepository) {
        return args -> {
        	
        	
        	
        	// save a couple of Participants
        	Participant tuteur = new Participant("Jack", "Bauer");
			participantService.addParticipant(tuteur, Statut.TUTEUR);
			Participant formateur = new Participant("Chloe", "O'Brian");
			participantService.addParticipant(formateur, Statut.FORMATEUR);
			Participant participant1 = new Participant("Kim", "Bauer");
			participantService.addParticipant(participant1, Statut.PARTICIPANT);
			Participant participant2 = new Participant("David", "Palmer");
			participantService.addParticipant(participant2, Statut.PARTICIPANT);
			Participant formateur2 = new Participant("Michelle", "Dessler");
			participantService.addParticipant(formateur2, Statut.FORMATEUR);

			// save formation
			
			Date dateFormation1 = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/2017");
			formationRepository.save(new Formation(dateFormation1,TypeFormation.TRANSMISSION_REFERENTIEL,tuteur,formateur,participant1,participant2));
			
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
