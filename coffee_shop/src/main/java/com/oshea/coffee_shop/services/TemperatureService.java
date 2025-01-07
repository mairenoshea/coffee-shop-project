package com.oshea.coffee_shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshea.coffee_shop.models.Temperature;
import com.oshea.coffee_shop.repositories.TemperatureRepository;

@Service
public class TemperatureService {
	
	@Autowired
	TemperatureRepository temperatureRepo;
	
	public List<Temperature> getAll() {
		return temperatureRepo.findAll();
	}
	
	public Temperature findById(Long id) {
		return temperatureRepo.findById(id).get();
	}
	

	}

