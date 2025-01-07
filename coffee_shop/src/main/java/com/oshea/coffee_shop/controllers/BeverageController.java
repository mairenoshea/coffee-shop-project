package com.oshea.coffee_shop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oshea.coffee_shop.models.Beverage;
import com.oshea.coffee_shop.models.BeverageTemperatureDrinkSize;
import com.oshea.coffee_shop.models.DrinkSize;
import com.oshea.coffee_shop.models.TemperatureDrinkSize;
import com.oshea.coffee_shop.models.User;
import com.oshea.coffee_shop.services.BeverageService;
import com.oshea.coffee_shop.services.BeverageTemperatureDrinkSizeService;
import com.oshea.coffee_shop.services.DrinkSizeService;
import com.oshea.coffee_shop.services.TemperatureDrinkSizeService;
import com.oshea.coffee_shop.services.TemperatureService;
import com.oshea.coffee_shop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BeverageController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	BeverageService beverageServ;
	
	@Autowired
	TemperatureService temperatureServ;
	
	@Autowired
	DrinkSizeService drinkSizeServ;
	
	@Autowired
	TemperatureDrinkSizeService temperatureDrinkSizeServ;
	
	@Autowired
	BeverageTemperatureDrinkSizeService bevTempDSServ;
	
	@GetMapping("/beverages/new")
	public String newBeverage(@ModelAttribute("newBeverage") Beverage newBeverage, Model model, HttpSession session) {
		
		System.out.println(session.getAttribute("user_id"));
		
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		
		
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			List<TemperatureDrinkSize> temperaturesDrinkSizes = temperatureDrinkSizeServ.getAll();
			temperaturesDrinkSizes.sort((p1,p2)->p1.getDrinkSize().getVolume().compareTo(p2.getDrinkSize().getVolume()));
			
			model.addAttribute("user",user);
			model.addAttribute("temperatures", temperatureServ.getAll());
			model.addAttribute("temperaturesDrinkSizes",temperaturesDrinkSizes);
			return "new_beverage.jsp";
		}
	}
	
	@PostMapping("/beverages") 
	public String createBeverage(@Valid @ModelAttribute("newBeverage") Beverage newBeverage, BindingResult result, @RequestParam("description") String newDescription, @RequestParam("temperatureDrinkSizes_ids") List<Long> temperatureDrinkSizes_ids, Model model, HttpSession session) {
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		
		User thisUser = userServ.findById((Long) session.getAttribute("user_id"));
		newBeverage.setDescription(newDescription);
		
	
		ArrayList<TemperatureDrinkSize> temperaturesDrinkSizesSelected = new ArrayList<TemperatureDrinkSize>();
		
		ArrayList<BeverageTemperatureDrinkSize> beveragesTemperaturesDrinkSizesSelected = new ArrayList<BeverageTemperatureDrinkSize>();
		
		
		//for every temperature Drink size combo selected,
		
		for(var i=0;i<temperatureDrinkSizes_ids.size(); i++) {
			/// add the temperature drink size combo to the list
			temperaturesDrinkSizesSelected.add(temperatureDrinkSizeServ.findById(temperatureDrinkSizes_ids.get(i)));
			
			BeverageTemperatureDrinkSize newBevTempDS = new BeverageTemperatureDrinkSize();
	
			newBevTempDS.setTemperatureDrinkSize(temperatureDrinkSizeServ.findById(temperatureDrinkSizes_ids.get(i)));
			newBevTempDS.setBasePrice((float) 0);
			beveragesTemperaturesDrinkSizesSelected.add(newBevTempDS);
		}
		
		
		
		newBeverage.setIsTemplate(true);
		System.out.println(temperatureDrinkSizes_ids);
		newBeverage.setPossibleTemperaturesDrinkSizes(beveragesTemperaturesDrinkSizesSelected);
		
		Beverage beverage = beverageServ.createOrUpdateBeverage(newBeverage, result);
		
		 if(result.hasErrors()) {
	         model.addAttribute("newBeverage", new Beverage());
	         model.addAttribute("user", thisUser);
	         System.out.println(result.getAllErrors());
	         return "new_beverage.jsp";
	     }
		 else {
			 for(var j=0;j<temperaturesDrinkSizesSelected.size();j++) {
					beveragesTemperaturesDrinkSizesSelected.get(j).setBeverage(beverage);
					
					BeverageTemperatureDrinkSize newBevTempDS = bevTempDSServ.createBevTempDS(beveragesTemperaturesDrinkSizesSelected.get(j));
					
					System.out.println(newBevTempDS.getTemperatureDrinkSize().getTemperature().getName() + " - " + newBevTempDS.getTemperatureDrinkSize().getDrinkSize().getName());
				}
		return "redirect:/dashboard"; 
		}
	}

	@GetMapping("/beverages/{id}")
	public String showBeverage(@PathVariable("id") Long beverage_id, Model model, HttpSession session) {
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		
		Beverage beverage = beverageServ.findById(beverage_id);
		User user = userServ.findById((Long) session.getAttribute("user_id")); 
		
		model.addAttribute("beverage",beverage);
		model.addAttribute("user",user);
		return "show_beverage.jsp";
	}
	
	
	
	@GetMapping("/beverages/{id}/edit")
	public String editBeverage(@PathVariable("id") Long beverage_id, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		else {
		
		Beverage existingBeverage = beverageServ.findById(beverage_id);
		User user = userServ.findById((Long) session.getAttribute("user_id")); 
		
		
		
		List<TemperatureDrinkSize> temperaturesDrinkSizes = temperatureDrinkSizeServ.getAll();
		
		
		temperaturesDrinkSizes.sort((p1,p2)->p1.getDrinkSize().getVolume().compareTo(p2.getDrinkSize().getVolume()));
		
		
		List<DrinkSize> hotDrinkSizes = new ArrayList<DrinkSize>();
		
		List<DrinkSize> icedDrinkSizes = new ArrayList<DrinkSize>();
		
		List<DrinkSize> blendedDrinkSizes = new ArrayList<DrinkSize>();
		
		for(var k=0;k<temperaturesDrinkSizes.size(); k++) {
			if(temperaturesDrinkSizes.get(k).getTemperature().getId()==1) {
				hotDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
			}
			else if(temperaturesDrinkSizes.get(k).getTemperature().getId()==2) {
				icedDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
			}
			else if(temperaturesDrinkSizes.get(k).getTemperature().getId()==3) {
				blendedDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
			}
		}
		
		BeverageTemperatureDrinkSize bevTempDS=new BeverageTemperatureDrinkSize();
		bevTempDS.setBeverage(existingBeverage);
		
		model.addAttribute("existingBeverage",existingBeverage);
		model.addAttribute("description",existingBeverage.getDescription());
		model.addAttribute("user",user);
		model.addAttribute("temperatures", temperatureServ.getAll());
		model.addAttribute("temperaturesDrinkSizes",temperaturesDrinkSizes);
		
		model.addAttribute("hotDrinkSizes", hotDrinkSizes);
		model.addAttribute("icedDrinkSizes",icedDrinkSizes);
		model.addAttribute("blendedDrinkSizes",blendedDrinkSizes);
		
		model.addAttribute("beveragesTemperaturesDrinkSizes",bevTempDSServ.getAll());
		model.addAttribute("bevTempDS", bevTempDS);
		
		//model.addAttribute("bevTempDSs", bevTempDSs);
		
		return "edit_beverage.jsp";
		}}
	
	

	@PutMapping("/beverages/{id}/edit")
	public String updateBeverage(@Valid @ModelAttribute("existingBeverage") Beverage existingBeverage, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/logout";
		}
		User thisUser = userServ.findById((Long) session.getAttribute("user_id"));
		
//		ArrayList<TemperatureDrinkSize> temperaturesDrinkSizesSelected = new ArrayList<TemperatureDrinkSize>();
//		
//		ArrayList<BeverageTemperatureDrinkSize> beveragesTemperaturesDrinkSizesSelected = new ArrayList<BeverageTemperatureDrinkSize>();
//		
//		
//		//for every temperature Drink size combo selected,
//		
//		for(var i=0;i<temperatureDrinkSizes_ids.size(); i++) {
//			/// add the temperature drink size combo to the list
//			temperaturesDrinkSizesSelected.add(temperatureDrinkSizeServ.findById(temperatureDrinkSizes_ids.get(i)));
//			System.out.println(temperaturesDrinkSizesSelected);
//			
//			BeverageTemperatureDrinkSize newBevTempDS = new BeverageTemperatureDrinkSize();
//			
//			newBevTempDS.setTemperatureDrinkSize(temperatureDrinkSizeServ.findById(temperatureDrinkSizes_ids.get(i)));
//			newBevTempDS.setBeverage(existingBeverage);
//			beveragesTemperaturesDrinkSizesSelected.add(newBevTempDS);
//		}
//		
//		
//		
//		existingBeverage.setIsTemplate(true);
//		System.out.println(beveragesTemperaturesDrinkSizesSelected);
//		existingBeverage.setPossibleTemperaturesDrinkSizes(beveragesTemperaturesDrinkSizesSelected);
//		
//		
		Beverage savedBeverage = beverageServ.createOrUpdateBeverage(existingBeverage, result);
		System.out.println(savedBeverage.getId());
		
		 if(result.hasErrors()) {
	         model.addAttribute("existingBeverage", existingBeverage);
	         model.addAttribute("user", thisUser);
	         System.out.println(result.getAllErrors());
	         return "edit_beverage.jsp";
	     }
		 else {
			 model.addAttribute("existingBeverage",savedBeverage);
			 
				
			 
			 return "redirect:/beverages/"+savedBeverage.getId(); 
		}
	}
	@PostMapping("/beverages/{beverage_id}/setPrices")
	public String setPrices(Model model, @PathVariable("id") Long beverage_id, @RequestParam("temperatureDrinkSizes_ids") List<Long> temperatureDrinkSizes_ids, @RequestParam("basePrices") List<Float> basePrices, HttpSession session) {
		System.out.println(temperatureDrinkSizes_ids);
		System.out.println(basePrices);
		return "redirect:/beverages/"+beverage_id+"/edit";
		
	}
	
	@DeleteMapping("/beverages/{id}")
	public String delete(Model model, @PathVariable("id") Long id, HttpSession session) {
		beverageServ.deleteById(id);
		return "redirect:/menu";
	}
	
	@PutMapping("/bevTempDS/{id}")
	public String updateBevTempDS(@ModelAttribute("bevTempDS") BeverageTemperatureDrinkSize bevTempDS, BindingResult result, Model model,@PathVariable("id") Long bevTempDS_id, @RequestParam("beverage_id") Long beverage_id, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			System.out.println("no user logged in - redirecting");
			return "redirect:/";
		}
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			
			bevTempDS.setId(bevTempDS_id);
			System.out.println(bevTempDS_id);
			BeverageTemperatureDrinkSize savedBevTempDS = bevTempDSServ.update(bevTempDS,result); 
			
			Beverage thisBeverage= beverageServ.findById(beverage_id);
			
			if (thisBeverage.getPossibleTemperaturesDrinkSizes()!=null) {
				List<BeverageTemperatureDrinkSize> existingBevTempDSs = thisBeverage.getPossibleTemperaturesDrinkSizes();
				existingBevTempDSs.add(savedBevTempDS);
				thisBeverage.setPossibleTemperaturesDrinkSizes(existingBevTempDSs);
			}
			else {
				List<BeverageTemperatureDrinkSize> myList = new ArrayList<BeverageTemperatureDrinkSize>();
				myList.add(savedBevTempDS);
				thisBeverage.setPossibleTemperaturesDrinkSizes(myList);
			}
			
			Beverage existingBeverage = beverageServ.update(thisBeverage);
			
			List<TemperatureDrinkSize> temperaturesDrinkSizes = temperatureDrinkSizeServ.getAll();
			
			List<BeverageTemperatureDrinkSize> bevTempDSs = bevTempDSServ.findAllByBeverageId(beverage_id);
			
			temperaturesDrinkSizes.sort((p1,p2)->p1.getDrinkSize().getVolume().compareTo(p2.getDrinkSize().getVolume()));
			
			bevTempDSs.sort((p1,p2)->p1.getTemperatureDrinkSize().getDrinkSize().getVolume().compareTo(p2.getTemperatureDrinkSize().getDrinkSize().getVolume()));
			System.out.println(bevTempDSs);
			
			List<DrinkSize> hotDrinkSizes = new ArrayList<DrinkSize>();
			
			List<DrinkSize> icedDrinkSizes = new ArrayList<DrinkSize>();
			
			List<DrinkSize> blendedDrinkSizes = new ArrayList<DrinkSize>();
			
			for(var k=0;k<temperaturesDrinkSizes.size(); k++) {
				if(temperaturesDrinkSizes.get(k).getTemperature().getId()==1) {
					hotDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
				}
				else if(temperaturesDrinkSizes.get(k).getTemperature().getId()==2) {
					icedDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
				}
				else if(temperaturesDrinkSizes.get(k).getTemperature().getId()==3) {
					blendedDrinkSizes.add(temperaturesDrinkSizes.get(k).getDrinkSize());
				}
			}
			
			model.addAttribute("existingBeverage",beverageServ.findById(beverage_id));
			model.addAttribute("description",beverageServ.findById(beverage_id).getDescription());
			model.addAttribute("user",user);
			model.addAttribute("temperatures", temperatureServ.getAll());
			model.addAttribute("temperaturesDrinkSizes",temperaturesDrinkSizes);
			
			model.addAttribute("hotDrinkSizes", hotDrinkSizes);
			model.addAttribute("icedDrinkSizes",icedDrinkSizes);
			model.addAttribute("blendedDrinkSizes",blendedDrinkSizes);
			
			model.addAttribute("beveragesTemperaturesDrinkSizes",bevTempDSServ.getAll());
			model.addAttribute("bevTempDS", new BeverageTemperatureDrinkSize());
			
			return "redirect:/beverages/"+existingBeverage.getId()+"/edit";
		}
	}
	
}
