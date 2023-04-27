package com.hendisantika.adminlte.repository;

import com.hendisantika.adminlte.model.Nationalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NationaliteRepository extends JpaRepository<Nationalite, Long>{

}
