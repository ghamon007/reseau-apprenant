package fr.admr.reseau.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	private Statut statutCourant;

	@OneToMany(mappedBy="participant", cascade = CascadeType.ALL, orphanRemoval=false)
	private List<HistoriqueStatut> historiqueStatuts = null;
	
	
	public Participant(){
		this.historiqueStatuts = new ArrayList<HistoriqueStatut>();
	}
	
	public Participant(String unNom, String unPrenom){
		this.nom = unNom;
		this.prenom = unPrenom;
		this.historiqueStatuts = new ArrayList<HistoriqueStatut>();
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

	public List<HistoriqueStatut> getHistoriqueStatuts() {
		return historiqueStatuts;
	}

	public void setHistoriqueStatuts(List<HistoriqueStatut> historiqueStatuts) {
		this.historiqueStatuts = historiqueStatuts;
	}

	public Statut getStatutCourant() {
		return statutCourant;
	}

	public void setStatutCourant(Statut statutCourant) {
		this.statutCourant = statutCourant;
	}

	



}
