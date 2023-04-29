package com.hendisantika.adminlte.model;

import java.time.LocalTime;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Seance extends AbstractModel<Long>{
	private static final long serialVersionUID = 6992208427439369561L;

	@Column(name = "date_projection")
    private Date dateProjection;
	
	@Column(name = "heure_debut")
    private LocalTime heureDebut;
	
	@Column(name = "heure_fin")
    private LocalTime heureFin;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Film_ID")
    private Film film;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="salle_ID")
    private Salle salle;
}
