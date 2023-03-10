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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tracker")
@Tag(name = "Tracker Controller", description = "Operaciones para el manejo del traker de geolocalización")
public class TrackerController {

	@Autowired
	private IDispositivoService loginService;

	@Autowired
	private ITrackerService trackerService;

	@Operation(summary = "Obtener acceso al traker")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Objeto recuperado exitosamente", content = @Content()),
			@ApiResponse(responseCode = "401", description = "No estas autorizado para ver este recurso", content = @Content()),
			@ApiResponse(responseCode = "403", description = "Está prohibido acceder al recurso que estaba tratando de alcanzar", content = @Content()),
			@ApiResponse(responseCode = "404", description = "No se encuentra el recurso que intentabas alcanzar", content = @Content()) })
	@PostMapping("/login")
	public ResponseEntity<Dispositivo> obtenerPorCorreo(HttpServletRequest request) throws Exception {
		String correo = request.getParameter("correo");
		String token = request.getParameter("token");
		Dispositivo obj = loginService.loginDispositivo(correo, token);

		if (obj == null) {
			throw new ModeloNotFoundException("DISPOSITIVO NO ENCONTRADO");
		}

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@Operation(summary = "Listar todas las geolocalizaciones")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente", content = @Content()),
			@ApiResponse(responseCode = "401", description = "No estas autorizado para ver este recurso", content = @Content()),
			@ApiResponse(responseCode = "403", description = "Está prohibido acceder al recurso que estaba tratando de alcanzar", content = @Content()),
			@ApiResponse(responseCode = "404", description = "No se encuentra el recurso que intentabas alcanzar", content = @Content()) })
	@GetMapping("/listar")
	public ResponseEntity<List<Tracker>> listar() throws Exception {
		List<Tracker> lista = trackerService.listar();
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@Operation(summary = "Registrar traker geolocalización")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Registrado con éxito.", content = @Content()),
			@ApiResponse(responseCode = "401", description = "No estas autorizado para acceder a este recurso", content = @Content()),
			@ApiResponse(responseCode = "403", description = "Está prohibido acceder al recurso", content = @Content()),
			@ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content()) })
	@PostMapping("/registrar")
	public ResponseEntity<Tracker> registrar(@Valid @RequestBody Tracker data) throws Exception {
		data.setFecha(LocalDateTime.now());

		Tracker obj = trackerService.registrar(data);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
}
