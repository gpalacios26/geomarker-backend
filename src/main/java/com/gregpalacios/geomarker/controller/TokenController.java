package com.gregpalacios.geomarker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tokens")
@Tag(name = "Token Controller", description = "Operaciones para el manejo de los token")
public class TokenController {

	@Autowired
	private ConsumerTokenServices tokenServices;
		
	@GetMapping("/anular/{tokenId:.*}")
	public void revocarToken(@PathVariable("tokenId") String token) {
		tokenServices.revokeToken(token);
	}
}
