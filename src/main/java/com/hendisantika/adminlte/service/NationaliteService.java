package com.hendisantika.adminlte.service;

import com.hendisantika.adminlte.model.Nationalite;
import com.hendisantika.adminlte.repository.NationaliteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class NationaliteService extends AbstractService<Nationalite, Long>{

	@Autowired
	private NationaliteRepository nationaliteRepository;
	
	@Override
	protected JpaRepository<Nationalite, Long> getRepository() {
		// TODO Auto-generated method stub
		return nationaliteRepository;
	}

}
