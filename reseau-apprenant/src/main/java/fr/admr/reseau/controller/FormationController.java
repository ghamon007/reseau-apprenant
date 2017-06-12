package fr.admr.reseau.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.domain.TypeFormation;
import fr.admr.reseau.repository.FormationRepository;
import fr.admr.reseau.repository.ParticipantRepository;

@Controller
public class FormationController {
	

	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	private ParticipantRepository participantRepository;

		
	
	@RequestMapping("/formation/list")
    public String listeformation(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("formations", formationRepository.findAll());
        return "formations";
    }
	
	@RequestMapping("/formation/edit")
    public String formations(@RequestParam(value="id", required=false) Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("formation", formationRepository.getOne(id));
        model.addAttribute("tuteurs", getTuteurs());
        model.addAttribute("formateurs", getFormateurs());
        model.addAttribute("participants", getParticipants());
        return "formation";
    }

	@RequestMapping("/formation/update")
    public String updateFormation(@RequestParam(value="formation_id", required=true) Long id,
    		@RequestParam(value="formation_nom", required=true) String nom, 
    		@RequestParam(value="formation_prenom", required=false) String prenom,
    		Model model) {
		Formation formation = formationRepository.findOne(id);
        formationRepository.save(formation);
        model.addAttribute("message", "Mise à  jour de l'utilisateur : "+id);
        return "formation";
    }

	@RequestMapping("/formation/delete")
    public String deleteFormation(@RequestParam(value="id", required=true) Long id, Model model) {
        model.addAttribute("id", id);
        formationRepository.delete(id);
        model.addAttribute("message", "Suppression de l'utilisateur : "+id);
        return "formation";
    }

	
	@RequestMapping("/formation/new")
    public String newFormation(@RequestParam(value="formation_id", required=false) Long id, 
    		@RequestParam(value="formation_typeFormation", required=false) TypeFormation typeFormation,
    		@RequestParam(value="formation_dateFormation", required=false) Date dateFormation,
    		Model model) {
        model.addAttribute("formation", new Formation());
        model.addAttribute("message", "Suppression de l'utilisateur : "+id);
        return "formation";
    }
	
	public List<Participant> getTuteurs(){
		return participantRepository.findByStatut(Statut.TUTEUR);
	}

	public List<Participant> getFormateurs(){
		return participantRepository.findByStatut(Statut.FORMATEUR);
	}
	
	public List<Participant> getParticipants(){
		return participantRepository.findByStatut(Statut.PARTICIPANT);
	}

}
