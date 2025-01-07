package com.oshea.coffee_shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oshea.coffee_shop.models.BeverageTemperatureDrinkSize;
import com.oshea.coffee_shop.repositories.BeverageTemperatureDrinkSizeRepository;

@Service
public class BeverageTemperatureDrinkSizeService {
	@Autowired
	BeverageTemperatureDrinkSizeRepository bevTempDSRepo;
	
	public List<BeverageTemperatureDrinkSize> getAll() {
		return bevTempDSRepo.findAll();
	}
	
	public List<BeverageTemperatureDrinkSize> findAllByBeverageId(Long beverage_id) {
		return bevTempDSRepo.findAllByBeverageId(beverage_id);
	}
	
	public BeverageTemperatureDrinkSize createBevTempDS(BeverageTemperatureDrinkSize bevTempDS) {
		
			bevTempDSRepo.save(bevTempDS);
			return bevTempDS;
	
	}
	
	public BeverageTemperatureDrinkSize update(BeverageTemperatureDrinkSize bevTempDS, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return null;
		}
		else {
		bevTempDSRepo.save(bevTempDS);
		return bevTempDS;}
	}
	}
