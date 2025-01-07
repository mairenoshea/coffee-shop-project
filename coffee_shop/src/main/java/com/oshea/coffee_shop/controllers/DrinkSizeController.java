package com.oshea.coffee_shop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oshea.coffee_shop.models.DrinkSize;
import com.oshea.coffee_shop.models.Temperature;
import com.oshea.coffee_shop.models.TemperatureDrinkSize;
import com.oshea.coffee_shop.models.User;
import com.oshea.coffee_shop.services.DrinkSizeService;
import com.oshea.coffee_shop.services.TemperatureDrinkSizeService;
import com.oshea.coffee_shop.services.TemperatureService;
import com.oshea.coffee_shop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class DrinkSizeController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	DrinkSizeService drinkSizeServ;
	
	@Autowired
	TemperatureService temperatureServ;
	
	@Autowired
	TemperatureDrinkSizeService temperatureDrinkSizeServ;
	
	@GetMapping("/drinksizes/new")
	public String newDrinkSize(@ModelAttribute("newDrinkSize") DrinkSize newDrinkSize, BindingResult result, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			List<String> existingNames = drinkSizeServ.getAllNames();
			List<Float> existingVolumes = drinkSizeServ.getAllVolumes();
			model.addAttribute("existingNames", existingNames);
			model.addAttribute("existingVolumes", existingVolumes);
			model.addAttribute("user",user);
			return "new_drinksize.jsp";
		}
	}
	
	@PostMapping("/drinksizes")
	public String createDrinkSize(@Valid @ModelAttribute("newDrinkSize") DrinkSize newDrinkSize, BindingResult result, @RequestParam("temperature_ids") List<Long> temperature_ids, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		User thisUser = userServ.findById((Long) session.getAttribute("user_id"));
		
		ArrayList<Temperature> temperaturesSelected = new ArrayList<Temperature>();
		ArrayList<TemperatureDrinkSize> temperaturesDrinkSizesSelected = new ArrayList<TemperatureDrinkSize>();
		
		for(var i=0;i<temperature_ids.size(); i++) {
			temperaturesSelected.add(temperatureServ.findById(temperature_ids.get(i)));
			
			TemperatureDrinkSize newTemperatureDS = new TemperatureDrinkSize();
			
			newTemperatureDS.setTemperature(temperatureServ.findById(temperature_ids.get(i)));
			temperaturesDrinkSizesSelected.add(newTemperatureDS);
		}
		
		DrinkSize drinkSize = drinkSizeServ.createDrinkSize(newDrinkSize, result);
		
		 if(result.hasErrors()) {
	         model.addAttribute("newDrinkSize", newDrinkSize);
	         model.addAttribute("user", thisUser);
	         System.out.println(result.getAllErrors());
	         return "new_drinksize.jsp";
	     }
		 else {
			 for(var j=0;j<temperaturesDrinkSizesSelected.size();j++) {
					temperaturesDrinkSizesSelected.get(j).setDrinkSize(drinkSize);
					TemperatureDrinkSize newTempDS = temperatureDrinkSizeServ.createTemperatureDrinkSize(temperaturesDrinkSizesSelected.get(j));
					System.out.println(newTempDS.getTemperature().getName() + " - " + newTempDS.getDrinkSize().getName());
				}
			 return "redirect:/drinksizes"; 
		 }
	}
	
	@GetMapping("/drinksizes")
	public String showDrinkSizes(Model model, HttpSession session) {
		List<DrinkSize> allDrinkSizes = drinkSizeServ.getAll();
		allDrinkSizes.sort((p1,p2)->p1.getVolume().compareTo(p2.getVolume()));
		model.addAttribute("allDrinkSizes", allDrinkSizes);
		return "show_drinksizes.jsp";
	}
	
}
