package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.IngredientDescription;

@Repository
public interface IngredientDescriptionRepository extends JpaRepository<IngredientDescription, Integer> {

	
	
}
