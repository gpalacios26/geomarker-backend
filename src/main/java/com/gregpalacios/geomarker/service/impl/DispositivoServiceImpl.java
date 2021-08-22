package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Dispositivo;
import com.gregpalacios.geomarker.repo.IDispositivoRepo;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.service.IDispositivoService;

@Service
public class DispositivoServiceImpl extends CRUDImpl<Dispositivo, Integer> implements IDispositivoService {

	@Autowired
	private IDispositivoRepo repo;

	@Override
	protected IGenericRepo<Dispositivo, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public Page<Dispositivo> listarPageable(Pageable pageable) {
		return repo.listarPaginado(pageable);
	}

}
