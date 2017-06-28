package fr.admr.reseau.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.admr.reseau.domain.HistoriqueStatut;
import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;
import fr.admr.reseau.repository.HistoriqueStatutRepository;
import fr.admr.reseau.repository.ParticipantRepository;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private HistoriqueStatutRepository historiqueStatutRepository;

	public void updateParticipant(Participant unParticipant, Statut nouveauStatut) {
		List<HistoriqueStatut> historiqueStatuts = null;

		if (unParticipant.getId() == null) {
			historiqueStatuts = new ArrayList<HistoriqueStatut>();
			HistoriqueStatut historiqueStatut = new HistoriqueStatut(unParticipant, nouveauStatut, true);
			historiqueStatuts.add(historiqueStatut);
			unParticipant.setHistoriqueStatuts(historiqueStatuts);
		} else {
			historiqueStatuts = historiqueStatutRepository.findByParticipant(unParticipant);
			HistoriqueStatut historiqueStatutMaj = null;
			for (HistoriqueStatut historiqueStatut : historiqueStatuts) {
				if (nouveauStatut.equals(historiqueStatut.getStatut())) {
					historiqueStatutMaj = historiqueStatut;
					historiqueStatutMaj.setStatutCourant(true);
				} else {
					historiqueStatut.setStatutCourant(false);
				}
			}
			if (historiqueStatutMaj == null) {
				historiqueStatutMaj = new HistoriqueStatut(unParticipant, nouveauStatut, true);
				historiqueStatuts.add(historiqueStatutMaj);
			}
		}
		unParticipant.setHistoriqueStatuts(historiqueStatuts);
		participantRepository.save(unParticipant);

	}

	public void addParticipant(Participant unParticipant, Statut nouveauStatut) {
		List<HistoriqueStatut> historiqueStatuts = unParticipant.getHistoriqueStatuts();

		for (HistoriqueStatut historiqueStatut : historiqueStatuts) {
			historiqueStatut.setStatutCourant(false);
		}
		HistoriqueStatut historiqueStatut = new HistoriqueStatut(unParticipant, nouveauStatut, true);
		historiqueStatuts.add(historiqueStatut);
		unParticipant.setStatutCourant(nouveauStatut);
		participantRepository.save(unParticipant);
	}

}
