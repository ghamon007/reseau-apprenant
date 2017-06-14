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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Formation extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;


	@Enumerated(EnumType.STRING)
	@Column(nullable=false,columnDefinition = "ENUM('APPRENDRE_A_APPRENDRE', 'TRANSMISSION_REFERENTIEL')")
	TypeFormation typeFormation;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date dateFormation;
	
	@ManyToOne
	Participant tuteur;
	
	@ManyToOne
	Participant formateur;
	
	@ManyToOne(optional=true)
	Participant participant1;
	
	@ManyToOne(optional=true)
	Participant participant2;
	
	@Column
	private Boolean closed;
	
	public Formation(){
		
	}
	
	public Formation(Date dateFormation, TypeFormation typeFormation, Participant tuteur, Participant formateur, Participant participant1, Participant participant2){
		this.dateFormation = dateFormation;
		this.typeFormation = typeFormation;
		this.tuteur = tuteur;
		this.formateur = formateur;
		this.participant1 = participant1;
		this.participant2 = participant2;
		closed = false;
	}

	public TypeFormation getTypeFormation() {
		return typeFormation;
	}

	public void setTypeFormation(TypeFormation typeFormation) {
		this.typeFormation = typeFormation;
	}

	public Date getDateFormation() {
		return dateFormation;
	}

	public void setDateFormation(Date dateFormation) {
		this.dateFormation = dateFormation;
	}

	public Participant getTuteur() {
		return tuteur;
	}

	public void setTuteur(Participant tuteur) {
		this.tuteur = tuteur;
	}

	public Participant getFormateur() {
		return formateur;
	}

	public void setFormateur(Participant formateur) {
		this.formateur = formateur;
	}

	public Participant getParticipant1() {
		return participant1;
	}

	public void setParticipant1(Participant participant1) {
		this.participant1 = participant1;
	}

	public Participant getParticipant2() {
		return participant2;
	}

	public void setParticipant2(Participant participant2) {
		this.participant2 = participant2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Formation [id=" + id + ", typeFormation=" + typeFormation + ", dateFormation=" + dateFormation
				+ ", tuteur=" + tuteur.getId() + ", formateur=" + formateur.getId() + ", participant1=" + participant1.getId()
				+ ", participant2=" + participant2.getId() + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Formation other = (Formation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}


}
