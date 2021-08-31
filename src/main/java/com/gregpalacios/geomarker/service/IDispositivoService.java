package com.gregpalacios.geomarker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gregpalacios.geomarker.model.Dispositivo;

public interface IDispositivoService extends ICRUD<Dispositivo, Integer> {

	Page<Dispositivo> listarPageable(Pageable pageable);
	
	Dispositivo loginDispositivo(String correo, String token) throws Exception;
}
