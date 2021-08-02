package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Rol;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.repo.IRolRepo;
import com.gregpalacios.geomarker.service.IRolService;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {

	@Autowired
	private IRolRepo repo;
	
	@Override
	protected IGenericRepo<Rol, Integer> getRepo() {
		return repo;
	}

}
