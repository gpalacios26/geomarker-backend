package com.gregpalacios.geomarker.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gregpalacios.geomarker.model.Dispositivo;

public interface IDispositivoRepo extends IGenericRepo<Dispositivo, Integer> {

	@Query("SELECT d FROM Dispositivo d ORDER BY d.idDispositivo DESC")
	Page<Dispositivo> listarPaginado(Pageable pageable);
	
	@Query("SELECT d FROM Dispositivo d WHERE d.correo =:correo AND d.token =:token")
	Dispositivo loginDispositivo(@Param("correo")String correo, @Param("token")String token);
}
