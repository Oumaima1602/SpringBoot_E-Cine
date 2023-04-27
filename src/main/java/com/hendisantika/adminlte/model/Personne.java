package com.hendisantika.adminlte.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
public class Personne extends AbstractModel<Long>{

	private static final long serialVersionUID = -2974953413266908441L;

public enum TypePersonne {ACTEUR, REALISATEUR} 
	
	@Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;
    
    @Column(nullable = true, length = 100)
    private String photo;
    
    @Column(name = "date_naissance")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TypePersonne typePersonne;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="NATIONALITE_ID")
    private Nationalite nationalite;
    
    @ManyToMany(mappedBy="acteurs")
    private List<Film> films;
    
    @OneToMany(mappedBy = "realisateur")
	  private List<Film> filmsRealises;   
}
