package com.hendisantika.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.hendisantika.adminlte.model.Personne;
import com.hendisantika.adminlte.model.Personne.TypePersonne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
	
	List<Personne> findByTypePersonne(@RequestParam("typePersonne") TypePersonne typePersonne);
//	Page<Personne> findByDateNaissanceGreaterThanEqual(@RequestParam("dateNs") Date dateNs, Pageable pageable);
//	Page<Personne> findByNomContainingOrPrenomContaining(@RequestParam("nom") String nom,@RequestParam("prenom") String prenom, Pageable pageable);
//	List<Personne> findByTypePersonneIs(@RequestParam("typePersonne") TypePersonne typePersonne);
}
