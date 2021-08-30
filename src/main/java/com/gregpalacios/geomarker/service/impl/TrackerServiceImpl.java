package com.gregpalacios.geomarker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gregpalacios.geomarker.model.Tracker;
import com.gregpalacios.geomarker.repo.IGenericRepo;
import com.gregpalacios.geomarker.repo.ITrackerRepo;
import com.gregpalacios.geomarker.service.ITrackerService;

@Service
public class TrackerServiceImpl extends CRUDImpl<Tracker, Integer> implements ITrackerService {

	@Autowired
	private ITrackerRepo repo;
	
	@Override
	protected IGenericRepo<Tracker, Integer> getRepo() {
		return repo;
	}

}
