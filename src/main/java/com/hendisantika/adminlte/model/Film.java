package com.hendisantika.adminlte.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film extends AbstractModel<Long>{
	
	private static final long serialVersionUID = 2996009286487492970L;

	@Column(nullable = false, length = 50)
    private String titre;
	
	@Column(nullable = false)
    private int duree;

	@Column(nullable = false)
    private int annee;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GENRE_ID")
    private Genre genre;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NATIONALITE_ID")
    private Nationalite nationalite;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DIRECTOR_ID")
    private Personne realisateur;

    @ManyToMany
    @JoinTable(
      name="FILM_ACTEUR",
      joinColumns=@JoinColumn(name="ACTOR_ID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="FILM_ID", referencedColumnName="ID"))
    private List<Personne> acteurs;
    
    @OneToMany(mappedBy = "film")
	  private List<Seance> seances;
    
    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;
    
}
