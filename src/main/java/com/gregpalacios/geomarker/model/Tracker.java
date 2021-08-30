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
@Table(name = "tracker")
public class Tracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTracker;
	
	@Column(name = "direccion", nullable = true)
	private String direccion;
	
	@Column(name = "latitud", nullable = false)
	private Double latitud;
	
	@Column(name = "longitud", nullable = false)
	private Double longitud;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_dispositivo", nullable = false, foreignKey = @ForeignKey(name = "FK_tracker_dispositivo"))
	private Dispositivo dispositivo;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	public Integer getIdTracker() {
		return idTracker;
	}

	public void setIdTracker(Integer idTracker) {
		this.idTracker = idTracker;
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

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Tracker [idTracker=" + idTracker + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", dispositivo=" + dispositivo + ", fecha=" + fecha + "]";
	}
}
