package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.RecipeUnitIngredient;

@Repository
public interface RecipeUnitIngredientRepository extends JpaRepository<RecipeUnitIngredient,Integer> {

}
