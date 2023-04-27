package com.hendisantika.adminlte.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

public class Salle extends AbstractModel<Long>{
	@Column(nullable = false, length = 40)
    private int numero;

    @Column(nullable = false, length = 40)
    private int capacite;
    
    @OneToMany(mappedBy = "salle")
    @JsonIgnore
	private List<Seance> seances;

    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;
}
