package com.gregpalacios.geomarker.exception;

public class ModeloNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4037122593616095257L;

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}

}
