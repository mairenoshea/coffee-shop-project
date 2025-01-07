package com.oshea.coffee_shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshea.coffee_shop.models.TemperatureDrinkSize;
import com.oshea.coffee_shop.repositories.TemperatureDrinkSizeRepository;

@Service
public class TemperatureDrinkSizeService {
	@Autowired
	TemperatureDrinkSizeRepository temperatureDrinkSizeRepo;
	
	public List<TemperatureDrinkSize> getAll() {
		return temperatureDrinkSizeRepo.findAll();
	}
	
	public TemperatureDrinkSize findById(Long id) {
		return temperatureDrinkSizeRepo.findById(id).get();
	}
	
	public List<TemperatureDrinkSize> findByTemperatureId(Long temperature_id) {
		return temperatureDrinkSizeRepo.findByTemperatureId(temperature_id);
	}
	
	public TemperatureDrinkSize createTemperatureDrinkSize(TemperatureDrinkSize tempDS) {
		temperatureDrinkSizeRepo.save(tempDS);
		return tempDS;
	}
}
