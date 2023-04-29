package com.hendisantika.adminlte.repository;

import com.hendisantika.adminlte.model.Seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long>{

}
