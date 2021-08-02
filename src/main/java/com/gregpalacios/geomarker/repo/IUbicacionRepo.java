package com.gregpalacios.geomarker.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.gregpalacios.geomarker.model.Ubicacion;

public interface IUbicacionRepo extends IGenericRepo<Ubicacion, Integer> {

	@Query("SELECT u FROM Ubicacion u ORDER BY u.idUbicacion DESC")
	Page<Ubicacion> listarPaginado(Pageable pageable);
}
