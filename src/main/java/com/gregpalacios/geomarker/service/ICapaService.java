package com.gregpalacios.geomarker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gregpalacios.geomarker.model.Capa;

public interface ICapaService extends ICRUD<Capa, Integer> {

	Page<Capa> listarPageable(Pageable pageable);
}
