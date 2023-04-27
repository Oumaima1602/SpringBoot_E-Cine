package com.hendisantika.adminlte.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

public class Genre extends AbstractModel<Long>{
	@Column(nullable = false, length = 50)
    private String libelle;
	
	@OneToMany(mappedBy = "genre")
	  private List<Film> films;
}
