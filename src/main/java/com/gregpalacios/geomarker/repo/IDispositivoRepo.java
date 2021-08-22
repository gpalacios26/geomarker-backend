package com.gregpalacios.geomarker.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.gregpalacios.geomarker.model.Dispositivo;

public interface IDispositivoRepo extends IGenericRepo<Dispositivo, Integer> {

	@Query("SELECT d FROM Dispositivo d ORDER BY d.idDispositivo DESC")
	Page<Dispositivo> listarPaginado(Pageable pageable);
}
