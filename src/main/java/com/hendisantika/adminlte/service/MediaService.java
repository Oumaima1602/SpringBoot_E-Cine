package com.hendisantika.adminlte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.adminlte.model.Media;
import com.hendisantika.adminlte.repository.MediaRepository;



@Service
public class MediaService extends AbstractService<Media, Long> {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    protected JpaRepository<Media, Long> getRepository() {
        return mediaRepository;
    }

}