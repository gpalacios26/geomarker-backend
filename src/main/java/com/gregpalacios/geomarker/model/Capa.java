package com.gregpalacios.geomarker.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "capa")
public class Capa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCapa;

	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "icono", nullable = false)
	private String icono;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_capa_usuario"))
	private Usuario usuario;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	public Integer getIdCapa() {
		return idCapa;
	}

	public void setIdCapa(Integer idCapa) {
		this.idCapa = idCapa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Capa [idCapa=" + idCapa + ", nombre=" + nombre + ", icono=" + icono + ", usuario=" + usuario
				+ ", fecha=" + fecha + "]";
	}
}
