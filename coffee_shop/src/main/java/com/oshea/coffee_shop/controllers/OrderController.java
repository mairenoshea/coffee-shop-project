package com.oshea.coffee_shop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import com.oshea.coffee_shop.models.Beverage;
import com.oshea.coffee_shop.models.Order;
import com.oshea.coffee_shop.models.User;
import com.oshea.coffee_shop.services.BeverageService;
import com.oshea.coffee_shop.services.OrderService;
import com.oshea.coffee_shop.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	UserService userServ;
	
	@Autowired 
	BeverageService beverageServ;
	
	@Autowired
	OrderService orderServ; 
	

	@GetMapping("/dashboard") 
	public String dashboard(Model model, HttpSession session) {

		
	
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			//send in logged in user
			model.addAttribute("user",user);
			
			//create a new order
			Order thisOrder = new Order();
			//set the logged in user as the orderer
			thisOrder.setOrderedBy(user);
			//save to database
			orderServ.create(thisOrder);
			//save the order id to session
			session.setAttribute("order_id", thisOrder.getId());
			//send in the new order object 
			model.addAttribute("thisOrder", thisOrder);
			int orderItemsCount = 0;
			if(thisOrder.getBeverages() != null) {
				orderItemsCount = thisOrder.getBeverages().size();
			}
			
			model.addAttribute("orderItemsCount", orderItemsCount);
			return "dashboard.jsp";
		
		}
	
	}
	
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			Order order=orderServ.findById((Long) session.getAttribute("order_id"));
			int orderItemsCount = 0;
			if(order.getBeverages() != null) {
				orderItemsCount = order.getBeverages().size();
			}
			
			model.addAttribute("orderItemsCount", orderItemsCount);
			//send in logged in user
			model.addAttribute("user",user);
			model.addAttribute("order",order);
			return "cart.jsp";
		}
	}
	
	
	
	@GetMapping("/menu") 
	public String menu(Model model, HttpSession session) {
		Order thisOrder = new Order();
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			if(session.getAttribute("order_id")== null) {
				
				thisOrder.setOrderedBy(user);
				//save to database
				orderServ.create(thisOrder);
				//save the order id to session
				
				session.setAttribute("order_id", thisOrder.getId());
			
				
			}
			else {
				thisOrder = orderServ.findById((Long) session.getAttribute("order_id"));
			}
			
			
			List<Beverage> allTemplateBeverages = beverageServ.findAllTemplateBeverages();
		
			//send in the logged-in user
			model.addAttribute("user",user);
			//send in the current order from session 
			model.addAttribute("thisOrder",thisOrder);
			int orderItemsCount = 0;
			if(thisOrder.getBeverages() != null) {
				orderItemsCount = thisOrder.getBeverages().size();
			}
			
			model.addAttribute("orderItemsCount", orderItemsCount);
			
			
			//send in the empty object to bind to the jsp
			model.addAttribute("beverageToAdd", new Beverage());
			
			model.addAttribute("allBeverages", allTemplateBeverages);
			
			return "menu.jsp";
		
		}
	}
	
	@PutMapping("/order/add_item/random") 
	public String addRandomToOrder(Model model, HttpSession session) {
		
		return "menu.jsp";
	}
	
	@PutMapping("/order/add_item") 
	public String addToOrder(@ModelAttribute("beverageToAdd") Beverage beverageToAdd, BindingResult result, Model model, HttpSession session) {
		
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		else {
			User user=userServ.findById((Long) session.getAttribute("user_id"));
			Order thisOrder = orderServ.findById((Long) session.getAttribute("order_id"));
			Beverage savedBeverageToAdd = beverageServ.create(beverageToAdd);
			
//			if(result.hasErrors()) {
//				model.addAttribute("user", user);
//				model.addAttribute("thisOrder", thisOrder);
//				model.addAttribute("beverageToAdd", new Beverage());
//			
//				List<Beverage> allTemplateBeverages = beverageServ.findAllTemplateBeverages();
//				model.addAttribute("allBeverages", allTemplateBeverages);
//				
//				for (Object object : result.getAllErrors()) {
//				    if(object instanceof FieldError) {
//				        FieldError fieldError = (FieldError) object;
//
//				        System.out.println(fieldError.getCode());
//				    }
//
//				    if(object instanceof ObjectError) {
//				        ObjectError objectError = (ObjectError) object;
//
//				        System.out.println(objectError.getCode());
//				    }
//				}
//				System.out.println(result.getAllErrors());
//				return "menu.jsp";
		
		
			
			if(thisOrder.getBeverages() != null) {
				List<Beverage> existingBevs = thisOrder.getBeverages();
				existingBevs.add(savedBeverageToAdd);
				thisOrder.setBeverages(existingBevs);
			}
			
			else {
				List<Beverage> bevs = new ArrayList<Beverage>();
				bevs.add(savedBeverageToAdd);
				thisOrder.setBeverages(bevs);
			}
		
			orderServ.update(thisOrder);
			
			model.addAttribute("user",user);
			model.addAttribute("order", thisOrder);
		
			return "redirect:/cart";
		
		}
	}
}
