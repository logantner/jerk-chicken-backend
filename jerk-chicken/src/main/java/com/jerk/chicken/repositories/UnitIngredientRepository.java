package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.UnitIngredient;

@Repository
public interface UnitIngredientRepository extends JpaRepository<UnitIngredient,Integer> {
	UnitIngredient findByUnitIdAndIngredientId(int unitId, int ingredientId);
}
