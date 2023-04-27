package com.hendisantika.adminlte.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Nationalite extends AbstractModel<Long> {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	@JsonProperty
    private String libelle;
	
	@OneToMany(mappedBy = "nationalite")
	@JsonIgnore
    private List<Personne> personnes;
	
	public String toString() {
		return this.libelle;
	}
}
