package fr.admr.reseau.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(insertable = true, updatable = false)
	private Date dateCreation;
	
	@Column(insertable = false, updatable = true)
	private Date dateModification;
	
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	
	@PrePersist
	void preInsert() {
	   this.dateCreation = new Date();
	}
	
	@PreUpdate
	void preUpdate(){
		this.dateModification = new Date();
	}

}
