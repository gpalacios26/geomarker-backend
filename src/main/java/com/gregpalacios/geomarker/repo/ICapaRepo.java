package com.gregpalacios.geomarker.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.gregpalacios.geomarker.model.Capa;

public interface ICapaRepo extends IGenericRepo<Capa, Integer> {

	@Query("SELECT c FROM Capa c ORDER BY c.idCapa DESC")
	Page<Capa> listarPaginado(Pageable pageable);
}
