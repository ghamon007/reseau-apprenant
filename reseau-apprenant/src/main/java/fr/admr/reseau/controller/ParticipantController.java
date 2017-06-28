package fr.admr.reseau.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.admr.reseau.domain.HistoriqueStatut;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.repository.ParticipantRepository;
import fr.admr.reseau.service.ParticipantService;

@Controller
public class ParticipantController {
	

	@Autowired
	ParticipantRepository participantRepository;
	
	@Autowired
	ParticipantService participantService;
	
	@RequestMapping("/participant/list")
    public String getListeParticipants(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }
	
	@RequestMapping("/participant/edit")
    public String getParticipant(@RequestParam(value="id", required=false) Long id, Model model) {
		Participant participant = participantRepository.findOne(id);
		for (HistoriqueStatut historiqueStatut : participant.getHistoriqueStatuts()) {
			if (historiqueStatut.isStatutCourant()){
				participant.setStatutCourant(historiqueStatut.getStatut());
			}
		}
		
        model.addAttribute("id", id);
        model.addAttribute("participant", participantRepository.findOne(id));
        model.addAttribute("statuts", getStatuts());
        return "participant";
    }
	@RequestMapping("/participant/new")
    public String newParticipant(Model model) {
        model.addAttribute("participant", new Participant());
        model.addAttribute("statuts", getStatuts());
        return "participant";
    }
	
	@RequestMapping("/participant/update")
    public String updateParticipant(@RequestParam("delete") String delete,final Participant participant,
    		Model model) {
		if ("1".equals(delete)){
			participantRepository.delete(participant);
		} else {
			participantService.updateParticipant(participant, participant.getStatutCourant());
			model.addAttribute("message", "Mise à  jour de l'utilisateur : "+participant.getId());
		}
        
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }


	public List<String> getStatuts(){
		List<String> result = new ArrayList<String>();
		result.add("");
		for (Statut aStatut : Statut.values()) {
			result.add(aStatut.toString());
		}
		return result;
	}
	
}
