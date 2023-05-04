package com.hendisantika.adminlte.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Media extends AbstractModel<Long>{
	private static final long serialVersionUID = 3132573891761750069L;
	
	public enum TypeMedia {IMAGE, VIDEO, DOCUMENT,LINK} 
	
	@Column(nullable = false, length = 100)
    private String media;
	
	@Column(nullable = true, length = 50)
    @Enumerated(EnumType.STRING)
    private TypeMedia typeMedia;
	
	@Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="FILM_ID")
    private Film film;
}