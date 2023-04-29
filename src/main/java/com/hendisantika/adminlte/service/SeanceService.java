package com.hendisantika.adminlte.service;



import com.hendisantika.adminlte.model.Seance;
import com.hendisantika.adminlte.repository.SeanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SeanceService extends AbstractService<Seance, Long> {

    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    protected JpaRepository<Seance, Long> getRepository() {
        return seanceRepository;
    }

}
