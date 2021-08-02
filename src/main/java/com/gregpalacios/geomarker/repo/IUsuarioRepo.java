package com.gregpalacios.geomarker.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gregpalacios.geomarker.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer> {

	Usuario findOneByUsername(String username);
	
	@Query("SELECT us FROM Usuario us WHERE us.username =:correo")
	Usuario verificarCorreo(@Param("correo")String correo);
	
	@Query("SELECT us FROM Usuario us ORDER BY us.idUsuario DESC")
	Page<Usuario> listarPaginado(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario us SET us.enabled =:estado WHERE us.username =:correo")
	void cambiarEstado(@Param("estado") Boolean estado, @Param("correo") String correo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario us SET us.password =:clave WHERE us.username =:correo")
	void cambiarClave(@Param("clave") String clave, @Param("correo") String correo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario us SET us.nombres =:nombres, us.apellidos =:apellidos WHERE us.username =:correo")
	void cambiarDatos(@Param("nombres") String nombres, @Param("apellidos") String apellidos, @Param("correo") String correo);
}
