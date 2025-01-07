package com.oshea.coffee_shop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{
	Optional<Order> findById(Long id);
}
