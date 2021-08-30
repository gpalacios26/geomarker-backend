package com.gregpalacios.geomarker.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import com.gregpalacios.geomarker.model.Ubicacion;
import com.gregpalacios.geomarker.service.IUbicacionService;
import com.gregpalacios.geomarker.util.ExcelGeneratorUbicacion;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

	@Autowired
	private IUbicacionService service;
	
	@GetMapping
	public ResponseEntity<List<Ubicacion>> listar() throws Exception {
		List<Ubicacion> lista = service.listar();
		return new ResponseEntity<List<Ubicacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Ubicacion>> listarPageable(Pageable pageable) throws Exception {
		Page<Ubicacion> lista = service.listarPageable(pageable);
		return new ResponseEntity<Page<Ubicacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ubicacion> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Ubicacion obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<Ubicacion>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Ubicacion> registrar(@Valid @RequestBody Ubicacion data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Ubicacion obj = service.registrar(data);
		return new ResponseEntity<Ubicacion>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Ubicacion> modificar(@Valid @RequestBody Ubicacion data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Ubicacion obj = service.modificar(data);
		return new ResponseEntity<Ubicacion>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Ubicacion obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/descargar")
	public ResponseEntity<InputStreamResource> descargar() throws Exception, IOException {
		List<Ubicacion> lista = service.listar();
    
	    ByteArrayInputStream in = ExcelGeneratorUbicacion.listadoToExcel(lista);
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attachment; filename=ubicaciones.xlsx");
	    
	    return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
