package com.gregpalacios.geomarker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.gregpalacios.geomarker.dto.CapaFileDTO;
import com.gregpalacios.geomarker.util.FileDTO;
import com.gregpalacios.geomarker.util.FileUtil;
import com.gregpalacios.geomarker.model.Capa;
import com.gregpalacios.geomarker.service.ICapaService;

@RestController
@RequestMapping("/api/capas")
public class CapaController {

	@Value("${upload.path.iconos}")
	private String basePath;
	
	@Autowired
	private ICapaService service;
	
	@GetMapping
	public ResponseEntity<List<Capa>> listar() throws Exception {
		List<Capa> lista = service.listar();
		return new ResponseEntity<List<Capa>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Capa>> listarPageable(Pageable pageable) throws Exception {
		Page<Capa> lista = service.listarPageable(pageable);
		return new ResponseEntity<Page<Capa>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Capa> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Capa obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		return new ResponseEntity<Capa>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Capa> registrar(@Valid @RequestBody CapaFileDTO data) throws Exception {
		FileDTO fileDTO = data.getFileDTO();
		fileDTO.setFilePath(basePath);
		String fileName = FileUtil.createFileFromBase64(fileDTO);
		
		Capa capaData = data.getCapa();
		capaData.setIcono(fileName);
		capaData.setFecha(LocalDateTime.now());
		
		Capa obj = service.registrar(capaData);
		return new ResponseEntity<Capa>(obj, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Capa> modificar(@Valid @RequestBody CapaFileDTO data) throws Exception {
		FileDTO fileDTO = data.getFileDTO();
		fileDTO.setFilePath(basePath);
		String fileName = FileUtil.createFileFromBase64(fileDTO);
		
		Capa capaData = data.getCapa();
		capaData.setIcono(fileName);
		capaData.setFecha(LocalDateTime.now());
		
		Capa obj = service.modificar(capaData);
		return new ResponseEntity<Capa>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Capa obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/icono/{name}")
	public ResponseEntity<InputStreamResource> verIcono(@PathVariable("name") String name) throws Exception, IOException {
		File file = new File(basePath+name);
		InputStream in = new FileInputStream(file.getPath());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+name);
	    
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
