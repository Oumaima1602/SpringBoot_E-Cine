package com.hendisantika.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hendisantika.adminlte.model.Media;
import com.hendisantika.dto.InlineMedia;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineMedia.class)
public interface MediaRepository extends JpaRepository<Media, Long> {

}