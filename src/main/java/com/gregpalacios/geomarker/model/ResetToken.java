package com.gregpalacios.geomarker.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reset_token")
public class ResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String token;
	
	@OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_token_usuario"))
	private Usuario user;

	@Column(nullable = false)
	private LocalDateTime expiracion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public LocalDateTime getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(LocalDateTime expiracion) {
		this.expiracion = expiracion;
	}
	
	public void setExpiracion(int minutos) {	
		LocalDateTime hoy = LocalDateTime.now();
		LocalDateTime exp = hoy.plusMinutes(minutos);
		this.expiracion = exp;
	}
	
	public boolean estaExpirado() {
		return LocalDateTime.now().isAfter(this.expiracion);
	}

	@Override
	public String toString() {
		return "ResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expiracion=" + expiracion + "]";
	}
}
