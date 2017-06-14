package fr.admr.reseau.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HistoriqueStatut extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private Long id;

	
	@ManyToOne
	private Participant participant;
	

	@Enumerated(EnumType.STRING)
	@Column(nullable=false,columnDefinition = "ENUM('PARTICIPANT', 'FORMATEUR','TUTEUR')")
	private Statut statut;
	
	private Date dateStatut;
	
	private boolean statutCourant;

	public HistoriqueStatut(Participant unParticipant, Statut unStatut, boolean unStatutCourant) {
		this.participant = unParticipant;
		this.statut =  unStatut;
		this.statutCourant = unStatutCourant;
		this.dateStatut = new Date();
	}
	public HistoriqueStatut() {
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Date getDateStatut() {
		return dateStatut;
	}

	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	public boolean isStatutCourant() {
		return statutCourant;
	}

	public void setStatutCourant(boolean statutCourant) {
		this.statutCourant = statutCourant;
	}

	@Override
	public String toString() {
		return "HistoriqueStatut [participant=" + participant.toString() + ", statut=" + statut + ", dateStatut=" + dateStatut
				+ ", statutCourant=" + statutCourant + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoriqueStatut other = (HistoriqueStatut) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (statut != other.statut)
			return false;
		return true;
	}

}
