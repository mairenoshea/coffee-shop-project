package com.oshea.coffee_shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.BeverageTemperatureDrinkSize;

@Repository
public interface BeverageTemperatureDrinkSizeRepository extends CrudRepository<BeverageTemperatureDrinkSize,Long> {
	
	Optional<BeverageTemperatureDrinkSize> findByid(Long id);
	
	List<BeverageTemperatureDrinkSize> findAll();
	
	List<BeverageTemperatureDrinkSize> findAllByBeverageId(Long beverage_id);
}
