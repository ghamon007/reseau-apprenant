package fr.admr.reseau.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Participant extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	private String nom;
	private String prenom;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,columnDefinition = "ENUM('TUTEUR', 'FORMATEUR', 'PARTICIPANT')")
	private Statut statut;
	
	public Participant(){
		
	}
	
	public Participant(String unNom, String unPrenom, Statut unStatut){
		this.nom = unNom;
		this.prenom = unPrenom;
		this.statut = unStatut;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut aStatut) {
		this.statut = aStatut;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", statut=" + statut + "]";
	}

}
