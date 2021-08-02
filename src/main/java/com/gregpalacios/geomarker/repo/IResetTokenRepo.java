package com.gregpalacios.geomarker.repo;

import com.gregpalacios.geomarker.model.ResetToken;

public interface IResetTokenRepo extends IGenericRepo<ResetToken, Integer> {

	ResetToken findByToken(String token);
}
