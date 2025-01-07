package com.oshea.coffee_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshea.coffee_shop.models.Beverage;
import com.oshea.coffee_shop.models.Order;
import com.oshea.coffee_shop.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepo;
	
	public Order findById(Long id) {
		if(orderRepo.findById(id).isPresent()) {
    		return orderRepo.findById(id).get();
    	}
    	else {
    		return null;
    	}

	}
	
	public Order create(Order newOrder) {
		orderRepo.save(newOrder);
		return newOrder;
	}
	
	
	public Order update(Order existingOrder) {
		orderRepo.save(existingOrder);
		return existingOrder;
	}
	
}
