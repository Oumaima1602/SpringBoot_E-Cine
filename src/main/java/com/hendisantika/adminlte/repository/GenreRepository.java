package com.hendisantika.adminlte.repository;


import com.hendisantika.adminlte.model.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
