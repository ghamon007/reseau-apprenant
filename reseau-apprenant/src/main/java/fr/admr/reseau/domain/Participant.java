package fr.admr.reseau.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	

	@OneToMany
	private List<HistoriqueStatut> historiqueStatuts;
	
	
	public Participant(){
		
	}
	
	public Participant(String unNom, String unPrenom){
		this.nom = unNom;
		this.prenom = unPrenom;
		this.historiqueStatuts = new ArrayList<>();
		historiqueStatuts.add(new HistoriqueStatut(this,Statut.PARTICIPANT,true));
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", nom=" + nom + ", prenom=" + prenom  + "]";
	}
	



}
