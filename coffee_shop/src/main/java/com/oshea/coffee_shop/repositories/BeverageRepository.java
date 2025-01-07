package com.oshea.coffee_shop.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oshea.coffee_shop.models.Beverage;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage,Long> {
	Optional<Beverage> findById(Long id);
}
