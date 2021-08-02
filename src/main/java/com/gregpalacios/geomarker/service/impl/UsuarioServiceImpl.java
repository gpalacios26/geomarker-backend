package com.gregpalacios.geomarker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Usuario;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.repo.IUsuarioRepo;
import com.gregpalacios.geomarker.service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	protected IGenericRepo<Usuario, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("El usuario no existe", username));
		}
		
		if(!usuario.isEnabled()) {
			throw new UsernameNotFoundException(String.format("El usuario no se encuentra activo", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getDescripcion()));
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return ud;
	}

	@Override
	public Usuario verificarCorreo(String correo) throws Exception {
		return repo.verificarCorreo(correo);
	}

	@Override
	public Page<Usuario> listarPageable(Pageable pageable) {
		return repo.listarPaginado(pageable);
	}

	@Override
	public void cambiarEstado(Boolean estado, String correo) throws Exception {
		repo.cambiarEstado(estado, correo);
	}

	@Override
	public void cambiarClave(String clave, String correo) throws Exception {
		repo.cambiarClave(bcrypt.encode(clave), correo);
	}

	@Override
	public void cambiarDatos(String nombres, String apellidos, String correo) throws Exception {
		repo.cambiarDatos(nombres, apellidos, correo);
	}

}
