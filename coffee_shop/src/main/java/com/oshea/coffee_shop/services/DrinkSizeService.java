package com.oshea.coffee_shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.oshea.coffee_shop.models.DrinkSize;
import com.oshea.coffee_shop.repositories.DrinkSizeRepository;

@Service
public class DrinkSizeService {
	@Autowired
	DrinkSizeRepository drinkSizeRepo;
	
	public List<DrinkSize> getAll() {
		return drinkSizeRepo.findAll();
	}
	
	public DrinkSize findById(Long id) {
		if(drinkSizeRepo.findById(id).isPresent()) {
    		return drinkSizeRepo.findByid(id).get();
    	}
    	else {
    		return null;
    	}
	}
	
	public List<String> getAllNames() {
		List<DrinkSize> existingSizes = (List<DrinkSize>) drinkSizeRepo.findAll();
		List<String> allNames = new ArrayList<String>();
		for(var i=0;i<existingSizes.size();i++) {
			allNames.add(existingSizes.get(i).getName());
		}
		return allNames;
	}
	
	
	
	public List<Float> getAllVolumes() {
		List<DrinkSize> existingSizes = (List<DrinkSize>) drinkSizeRepo.findAll();
		List<Float> allVolumes = new ArrayList<Float>();
		for(var i=0;i<existingSizes.size();i++) {
			allVolumes.add(existingSizes.get(i).getVolume());
		}
		return allVolumes;
	}
	
	public DrinkSize createDrinkSize(DrinkSize drinkSize, BindingResult result) {
		if(result.hasErrors()) {
    		return null;
    	}
		
		else if(getAllNames().contains((String) drinkSize.getName())) {
			result.rejectValue("name", "Exists", "Size name is already taken.");
			return null;
		}
		
		else if(getAllVolumes().contains((Number) drinkSize.getVolume())) {
			result.rejectValue("volume", "Exists", "Volume is already associated with a size name.");
			return null;
		}
		
		else {
			drinkSizeRepo.save(drinkSize);
			return drinkSize;
		}
	}
	
	public DrinkSize updateDrinkSize(DrinkSize drinkSize) {
		
			drinkSizeRepo.save(drinkSize);
			return drinkSize;
		
	}
	
}
