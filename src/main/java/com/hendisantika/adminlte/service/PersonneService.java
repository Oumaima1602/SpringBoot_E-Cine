package com.hendisantika.adminlte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.hendisantika.adminlte.model.Personne;
import com.hendisantika.adminlte.model.Personne.TypePersonne;
import com.hendisantika.adminlte.repository.PersonneRepository;

@Service
public class PersonneService extends AbstractService<Personne, Long>{
	@Autowired
    private PersonneRepository personneRepository;

    @Override
    protected JpaRepository<Personne, Long> getRepository() {
        return personneRepository;
    }
    public List<Personne> getActeurs(){
  	  return personneRepository.findByTypePersonne(TypePersonne.ACTEUR);
    }
    
    
    public List<Personne> getDirector(){
  	  return personneRepository.findByTypePersonne(TypePersonne.REALISATEUR);
    }
}
