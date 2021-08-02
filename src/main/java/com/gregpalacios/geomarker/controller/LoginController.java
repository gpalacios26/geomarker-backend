package com.gregpalacios.geomarker.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregpalacios.geomarker.model.ResetToken;
import com.gregpalacios.geomarker.model.Usuario;
import com.gregpalacios.geomarker.service.IResetTokenService;
import com.gregpalacios.geomarker.service.IUsuarioService;
import com.gregpalacios.geomarker.util.EmailUtil;
import com.gregpalacios.geomarker.util.MailDTO;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private IUsuarioService service;

	@Autowired
	private IResetTokenService tokenService;

	@Autowired
	private EmailUtil emailUtil;

	@GetMapping("/correo/{correo}")
	public ResponseEntity<Integer> obtenerPorCorreo(@PathVariable("correo") String correo) throws Exception {
		int rpta = 0;

		Usuario obj = service.verificarCorreo(correo);
		if (obj != null) {
			rpta = 1;
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

	@PostMapping("/enviar/correo")
	public ResponseEntity<Integer> enviarCorreo(HttpServletRequest request) throws Exception {
		int rpta = 0;

		String correo = request.getParameter("correo");
		String webUrl = request.getParameter("webUrl");

		Usuario user = service.verificarCorreo(correo);

		if (user != null && user.getIdUsuario() > 0) {
			ResetToken token = new ResetToken();
			token.setToken(UUID.randomUUID().toString());
			token.setUser(user);
			token.setExpiracion(10);
			tokenService.guardar(token);

			MailDTO mailDTO = new MailDTO();
			mailDTO.setFrom("GeoMarker");
			mailDTO.setTo(user.getUsername());
			mailDTO.setSubject("GeoMarker - Restablecer Contraseña");

			Map<String, Object> model = new HashMap<>();
			String resetUrl = webUrl + "/#/recuperar/" + token.getToken();
			model.put("user", token.getUser().getNombres());
			model.put("resetUrl", resetUrl);
			model.put("webUrl", webUrl);
			mailDTO.setModel(model);

			emailUtil.enviarMail(mailDTO, "email/template-reset-clave");

			rpta = 1;
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

	@GetMapping("/verificar/{token}")
	public ResponseEntity<Integer> verificarToken(@PathVariable("token") String token) throws Exception {
		int rpta = 0;

		try {
			if (token != null && !token.isEmpty()) {
				ResetToken rt = tokenService.findByToken(token);
				if (rt != null && rt.getId() > 0) {
					if (!rt.estaExpirado()) {
						rpta = 1;
					}
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

	@PostMapping("/restablecer/{token}")
	public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token, HttpServletRequest request) throws Exception {
		int rpta = 0;

		String clave = request.getParameter("clave");

		try {
			ResetToken rt = tokenService.findByToken(token);
			service.cambiarClave(clave, rt.getUser().getUsername());
			tokenService.eliminar(rt);

			rpta = 1;
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
}
