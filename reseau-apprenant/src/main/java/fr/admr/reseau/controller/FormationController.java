package fr.admr.reseau.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.admr.reseau.domain.Formation;
import fr.admr.reseau.domain.HistoriqueStatut;
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
        model.addAttribute("formation", formationRepository.findOne(id));
        model.addAttribute("tuteurs", getTuteurs());
        model.addAttribute("formateurs", getFormateurs());
        model.addAttribute("participants", getParticipants());
        model.addAttribute("typeFormations", getTypeFormations());
        return "formation";
    }

	@RequestMapping("/formation/update")
    public String updateFormation(@RequestParam(value="action", required=true) String action,Formation formation,
    		Model model) {
		System.out.println("ID formation à bouger "+formation.getId());
		if ("1".equals(action)){
			model.addAttribute("message", "Mise à  jour de l'utilisateur : "+formation.getId());
			formationRepository.delete(formation);
		} else {
			
			if (formation.getClosed().booleanValue()){
				
			}
			formationRepository.save(formation);
			String message = "Mise à  jour de l'utilisateur : "+formation.toString();
			System.out.println(message);
			model.addAttribute("message", "Mise à  jour de l'utilisateur : "+message);
		}
		model.addAttribute("formations", formationRepository.findAll());
        return "formations";
    }

	
	public Iterable<Participant> getTuteurs(){
		return participantRepository.findAll();
	}

	public Iterable<Participant> getFormateurs(){
		return participantRepository.findAll();
	}
	
	public Iterable<Participant> getParticipants(){
		return participantRepository.findAll();
	}
	
	public TypeFormation[] getTypeFormations(){
		return TypeFormation.values();
	}


}
