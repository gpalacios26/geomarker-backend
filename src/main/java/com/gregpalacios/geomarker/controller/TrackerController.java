package com.gregpalacios.geomarker.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.geomarker.exception.ModeloNotFoundException;
import com.gregpalacios.geomarker.model.Dispositivo;
import com.gregpalacios.geomarker.model.Tracker;
import com.gregpalacios.geomarker.service.IDispositivoService;
import com.gregpalacios.geomarker.service.ITrackerService;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {
	
	@Autowired
	private IDispositivoService loginService;
	
	@Autowired
	private ITrackerService trackerService;
	
	@PostMapping("/login")
	public ResponseEntity<Dispositivo> obtenerPorCorreo(HttpServletRequest request) throws Exception {
		String correo = request.getParameter("correo");
		String token = request.getParameter("token");
		Dispositivo obj = loginService.loginDispositivo(correo, token);

		if (obj == null) {
			throw new ModeloNotFoundException("DISPOSITIVO NO ENCONTRADO");
		}

		return new ResponseEntity<Dispositivo>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Tracker>> listar() throws Exception {
		List<Tracker> lista = trackerService.listar();
		return new ResponseEntity<List<Tracker>>(lista, HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Tracker> registrar(@Valid @RequestBody Tracker data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Tracker obj = trackerService.registrar(data);
		return new ResponseEntity<Tracker>(obj, HttpStatus.CREATED);
	}
}
