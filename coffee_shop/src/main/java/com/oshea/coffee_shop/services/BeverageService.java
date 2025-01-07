package com.oshea.coffee_shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oshea.coffee_shop.models.Beverage;
import com.oshea.coffee_shop.repositories.BeverageRepository;

@Service
public class BeverageService {
	@Autowired
	BeverageRepository beverageRepo;
	
	@Autowired
	BeverageTemperatureDrinkSizeService bevTempDSServ;
	
	public Beverage findById(Long id) {
		if(beverageRepo.findById(id).isPresent()) {
    		return beverageRepo.findById(id).get();
    	}
    	else {
    		return null;
    	}

	}
	
	public List<Beverage> findAll() {
		return (List<Beverage>) beverageRepo.findAll();
	}
	
	public List<Beverage> findAllTemplateBeverages() {
		List<Beverage> allBeverages = findAll();
		List<Beverage> allTemplates = new ArrayList<Beverage>();
		for(var i=0;i<allBeverages.size();i++) {
			if(allBeverages.get(i).getIsTemplate() == null) {
				continue;
			}
			else if(allBeverages.get(i).getIsTemplate() == true) {
				allTemplates.add(allBeverages.get(i));
			}
			
		}
		return allTemplates;
	}
	public Beverage createOrUpdateBeverage(Beverage beverage, BindingResult result) {
		if(result.hasErrors()) {
    		return null;
    	}
		else {
			beverageRepo.save(beverage);
			return beverage;
		}
	}
	
	public Beverage create(Beverage beverage) {
		beverageRepo.save(beverage);
		return beverage;
	}
	
	public Beverage update(Beverage beverage) {
		beverageRepo.save(beverage);
		return beverage;
	}
	
	public Long deleteById(Long id) {
		Beverage bevToDelete = findById(id);
		bevToDelete.setPossibleTemperaturesDrinkSizes(null);
		for(var i=0; i< bevTempDSServ.findAllByBeverageId(id).size(); i++) {
			bevTempDSServ.findAllByBeverageId(id).get(i).setBeverage(null);
		}
		bevToDelete.setTemperatureDrinkSize(null);
		beverageRepo.save(bevToDelete);
		beverageRepo.delete(bevToDelete);
		return id;
	}
}
