package fr.admr.reseau.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.repository.ParticipantRepository;

@Controller
public class ParticipantController {
	

	@Autowired
	ParticipantRepository participantRepository;
	
	@RequestMapping("/participant/list")
    public String listeParticipant(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }
	
	@RequestMapping("/participant/edit")
    public String participants(@RequestParam(value="id", required=false) Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("participant", participantRepository.findOne(id));
        model.addAttribute("statuts", getStatuts());
        return "participant";
    }

	@RequestMapping("/participant/update")
    public String updateParticipant(@RequestParam("delete") String delete,final Participant participant,
    		Model model) {
		if ("1".equals(delete)){
			participantRepository.delete(participant);
		} else {
			participantRepository.save(participant);
			model.addAttribute("message", "Mise à  jour de l'utilisateur : "+participant.getId());
		}
        
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }


	public Statut[] getStatuts(){
		return Statut.values();
	}
	
}
