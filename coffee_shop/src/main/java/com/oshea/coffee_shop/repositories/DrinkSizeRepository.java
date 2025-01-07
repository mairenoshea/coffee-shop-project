package com.oshea.coffee_shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.DrinkSize;

@Repository
public interface DrinkSizeRepository extends CrudRepository<DrinkSize,Long>{
	Optional<DrinkSize> findByid(Long id);
	
	List<DrinkSize> findAll();
}
	
