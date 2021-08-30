package com.gregpalacios.geomarker.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.geomarker.model.Tracker;
import com.gregpalacios.geomarker.service.ITrackerService;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {
	
	@Autowired
	private ITrackerService service;
	
	@GetMapping
	public ResponseEntity<List<Tracker>> listar() throws Exception {
		List<Tracker> lista = service.listar();
		return new ResponseEntity<List<Tracker>>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Tracker> registrar(@Valid @RequestBody Tracker data) throws Exception {
		data.setFecha(LocalDateTime.now());
		
		Tracker obj = service.registrar(data);
		return new ResponseEntity<Tracker>(obj, HttpStatus.CREATED);
	}
}
