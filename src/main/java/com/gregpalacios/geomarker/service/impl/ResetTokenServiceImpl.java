package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.ResetToken;
import com.gregpalacios.geomarker.repo.IResetTokenRepo;
import com.gregpalacios.geomarker.service.IResetTokenService;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenRepo repo;
	
	@Override
	public ResetToken findByToken(String token) throws Exception {
		return repo.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) throws Exception {
		repo.save(token);
	}

	@Override
	public void eliminar(ResetToken token) throws Exception {
		repo.delete(token);
	}

}
