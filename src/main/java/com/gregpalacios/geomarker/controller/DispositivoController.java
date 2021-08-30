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
import com.gregpalacios.geomarker.model.Dispositivo;
import com.gregpalacios.geomarker.service.IDispositivoService;
import com.gregpalacios.geomarker.util.ExcelGeneratorDispositivo;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

	@Autowired
	private IDispositivoService service;
	
	@GetMapping
	public ResponseEntity<List<Dispositivo>> listar() throws Exception {
		List<Dispositivo> lista = service.listar();
		return new ResponseEntity<List<Dispositivo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Dispositivo>> listarPageable(Pageable pageable) throws Exception {
		Page<Dispositivo> lista = service.listarPageable(pageable);
		return new ResponseEntity<Page<Dispositivo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dispositivo> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Dispositivo obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<Dispositivo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Dispositivo> registrar(@Valid @RequestBody Dispositivo data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Dispositivo obj = service.registrar(data);
		return new ResponseEntity<Dispositivo>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Dispositivo> modificar(@Valid @RequestBody Dispositivo data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Dispositivo obj = service.modificar(data);
		return new ResponseEntity<Dispositivo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Dispositivo obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/descargar")
	public ResponseEntity<InputStreamResource> descargar() throws Exception, IOException {
		List<Dispositivo> lista = service.listar();
    
	    ByteArrayInputStream in = ExcelGeneratorDispositivo.listadoToExcel(lista);
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attachment; filename=dispositivos.xlsx");
	    
	    return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
