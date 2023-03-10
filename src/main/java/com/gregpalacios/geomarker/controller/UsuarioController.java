package com.gregpalacios.geomarker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.geomarker.exception.ModeloNotFoundException;
import com.gregpalacios.geomarker.model.Usuario;
import com.gregpalacios.geomarker.service.IUsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario Controller", description = "Operaciones para el manejo de la gestión de usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() throws Exception {
		List<Usuario> lista = service.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<Usuario>> listarPageable(Pageable pageable) throws Exception {
		Page<Usuario> lista = service.listarPageable(pageable);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Usuario obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario data) throws Exception {
		Usuario user = data;
		user.setPassword(bcrypt.encode(data.getPassword()));
		user.setEnabled(true);

		Usuario obj = service.registrar(user);
		obj.setPassword(null);

		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@GetMapping("/correo/{correo}")
	public ResponseEntity<Usuario> obtenerPorCorreo(@PathVariable("correo") String correo) throws Exception {
		Usuario obj = service.verificarCorreo(correo);

		if (obj == null) {
			throw new ModeloNotFoundException("CORREO NO ENCONTRADO " + correo);
		}

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PutMapping("/modificar/estado")
	public ResponseEntity<Integer> modificarEstado(@Valid @RequestBody Usuario data) throws Exception {
		int rpta = 0;

		try {
			service.cambiarEstado(data.isEnabled(), data.getUsername());
			rpta = 1;
		} catch (Exception e) {
			return new ResponseEntity<>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(rpta, HttpStatus.OK);
	}

	@PutMapping("/modificar/clave")
	public ResponseEntity<Integer> modificarClave(@Valid @RequestBody Usuario data) throws Exception {
		int rpta = 0;

		try {
			service.cambiarClave(data.getPassword(), data.getUsername());
			rpta = 1;
		} catch (Exception e) {
			return new ResponseEntity<>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(rpta, HttpStatus.OK);
	}

	@PutMapping("/modificar/perfil")
	public ResponseEntity<Integer> modificarPerfil(@Valid @RequestBody Usuario data) throws Exception {
		int rpta = 0;

		try {
			service.cambiarDatos(data.getNombres(), data.getApellidos(), data.getUsername());
			rpta = 1;
		} catch (Exception e) {
			return new ResponseEntity<>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(rpta, HttpStatus.OK);
	}
}
