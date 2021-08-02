package com.gregpalacios.geomarker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gregpalacios.geomarker.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer> {

	Usuario verificarCorreo(String correo) throws Exception;
	
	Page<Usuario> listarPageable(Pageable pageable);
	
	void cambiarEstado(Boolean estado, String correo) throws Exception;
	
	void cambiarClave(String clave, String correo) throws Exception;
	
	void cambiarDatos(String nombres, String apellidos, String correo) throws Exception;
}
