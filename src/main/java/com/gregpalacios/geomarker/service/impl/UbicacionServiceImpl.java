package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Ubicacion;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.repo.IUbicacionRepo;
import com.gregpalacios.geomarker.service.IUbicacionService;

@Service
public class UbicacionServiceImpl extends CRUDImpl<Ubicacion, Integer> implements IUbicacionService {

	@Autowired
	private IUbicacionRepo repo;

	@Override
	protected IGenericRepo<Ubicacion, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public Page<Ubicacion> listarPageable(Pageable pageable) {
		return repo.listarPaginado(pageable);
	}

}
