package com.oshea.coffee_shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.Temperature;

@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long>{
	Optional<Temperature> findById(Long id);
	
	Optional<Temperature> findByName(String name);
	
	List<Temperature> findAll();
	
}
