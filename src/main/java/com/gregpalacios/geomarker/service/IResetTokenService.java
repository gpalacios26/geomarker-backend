package com.gregpalacios.geomarker.service;

import com.gregpalacios.geomarker.model.ResetToken;

public interface IResetTokenService {

	ResetToken findByToken(String token) throws Exception;
	
	void guardar(ResetToken token) throws Exception;
	
	void eliminar(ResetToken token) throws Exception;
}
