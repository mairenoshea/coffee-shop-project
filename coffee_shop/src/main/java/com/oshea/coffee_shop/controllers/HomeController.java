package com.oshea.coffee_shop.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.oshea.coffee_shop.models.Beverage;
import com.oshea.coffee_shop.models.LoginUser;
import com.oshea.coffee_shop.models.Order;
import com.oshea.coffee_shop.models.User;
import com.oshea.coffee_shop.services.BeverageService;
import com.oshea.coffee_shop.services.OrderService;
import com.oshea.coffee_shop.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	BeverageService beverageServ;
	
	@Autowired
	OrderService orderServ;
	
	 
	 @GetMapping("/")
	 public String index(Model model, HttpSession session) {
		 if(session.getAttribute("user_id")!=null) {
			 User user = userServ.findById((Long) session.getAttribute("user_id"));
			 model.addAttribute("user", user);
			 return "dashboard.jsp";
		 }
		 else {
	     // Bind empty User and LoginUser objects to the JSP
	     // to capture the form input
	     model.addAttribute("newUser", new User());
	     model.addAttribute("newLogin", new LoginUser());
	     return "index.jsp";
		 }
	 }
	 
	
	 
	 @PostMapping("/register")
	 public String register(@Valid @ModelAttribute("newUser") User newUser, 
			 BindingResult result, Model model, HttpSession session) {
	     
	     // TO-DO Later -- call a register method in the service 
	     // to do some extra validations and create a new user!
	     userServ.register(newUser, result);
	     
	     if(result.hasErrors()) {
	         // Be sure to send in the empty LoginUser before 
	         // re-rendering the page.
	         model.addAttribute("newLogin", new LoginUser());
	         return "index.jsp";
	     }
	     
	     // No errors! 
	     Long user_id=newUser.getId();
	     session.setAttribute("user_id", user_id);
	   
	     return "redirect:/dashboard";
	 }

	 @PostMapping("/login")
	 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	         BindingResult result, Model model, HttpSession session) {

	
	    
	     
	     if(result.hasErrors()) {
	         model.addAttribute("newUser", new User());
	         return "redirect:/";
	     }
	 
	     // No errors! 
	     // TO-DO Later: Store their ID from the DB in session, 
	     // in other words, log them in.
	     //Long user_id=user.getId();
	     User user = userServ.login(newLogin, result);
	     session.setAttribute("user_id", user.getId());
	     
	     return "redirect:/dashboard";
	 } 
	

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	
	
}


