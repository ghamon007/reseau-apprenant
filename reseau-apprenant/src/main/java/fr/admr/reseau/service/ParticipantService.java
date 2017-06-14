package fr.admr.reseau.service;

import fr.admr.reseau.domain.Participant;
import fr.admr.reseau.domain.Statut;


public interface ParticipantService {

	void updateParticipant(Participant unParticipant, Statut nouveauStatut);

	void addParticipant(Participant unParticipant, Statut nouveauStatut);
}