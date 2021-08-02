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
@Table(name = "ubicacion")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUbicacion;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_capa", nullable = false, foreignKey = @ForeignKey(name = "FK_ubicacion_capa"))
	private Capa capa;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "latitud", nullable = false)
	private Double latitud;
	
	@Column(name = "longitud", nullable = false)
	private Double longitud;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_ubicacion_usuario"))
	private Usuario usuario;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	public Integer getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
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
		return "Ubicacion [idUbicacion=" + idUbicacion + ", capa=" + capa + ", titulo=" + titulo + ", descripcion="
				+ descripcion + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}
}
