package com.gregpalacios.geomarker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gregpalacios.geomarker.model.Ubicacion;

public interface IUbicacionService extends ICRUD<Ubicacion, Integer> {

	Page<Ubicacion> listarPageable(Pageable pageable);
}
