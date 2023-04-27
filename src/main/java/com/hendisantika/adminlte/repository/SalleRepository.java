package com.hendisantika.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hendisantika.adminlte.model.Salle;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

}
