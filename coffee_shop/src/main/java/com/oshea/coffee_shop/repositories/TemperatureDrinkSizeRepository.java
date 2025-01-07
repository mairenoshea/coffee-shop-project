package com.oshea.coffee_shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.TemperatureDrinkSize;

@Repository
public interface TemperatureDrinkSizeRepository extends CrudRepository<TemperatureDrinkSize,Long> {
	Optional<TemperatureDrinkSize> findById(Long id);
	
	List<TemperatureDrinkSize> findAll();
	
	List<TemperatureDrinkSize> findByTemperatureId(Long id);
}
