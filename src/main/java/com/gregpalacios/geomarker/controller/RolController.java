package com.gregpalacios.geomarker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.geomarker.exception.ModeloNotFoundException;
import com.gregpalacios.geomarker.model.Rol;
import com.gregpalacios.geomarker.service.IRolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

	@Autowired
	private IRolService service;
	
	@GetMapping
	public ResponseEntity<List<Rol>> listar() throws Exception {
		List<Rol> lista = service.listar();
		return new ResponseEntity<List<Rol>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Rol obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<Rol>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Rol> registrar(@Valid @RequestBody Rol data) throws Exception {
		Rol obj = service.registrar(data);
		return new ResponseEntity<Rol>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Rol> modificar(@Valid @RequestBody Rol data) throws Exception {
		Rol obj = service.modificar(data);
		return new ResponseEntity<Rol>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Rol obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
