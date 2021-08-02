package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Capa;
import com.gregpalacios.geomarker.repo.ICapaRepo;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.service.ICapaService;

@Service
public class CapaServiceImpl extends CRUDImpl<Capa, Integer> implements ICapaService {

	@Autowired
	private ICapaRepo repo;

	@Override
	protected IGenericRepo<Capa, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public Page<Capa> listarPageable(Pageable pageable) {
		return repo.listarPaginado(pageable);
	}

}
