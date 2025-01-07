package com.oshea.coffee_shop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
    // Check if the request is for a static resource
    if (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) != null) {
        int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == 404) {
            return "redirect:/"; // Redirect to index.html
        }
    }
    return "error"; // Default error page
}

	
	public String getErrorPath() {
    return "/error";
}
	
}
