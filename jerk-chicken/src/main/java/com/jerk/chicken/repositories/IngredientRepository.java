package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
	public List<Ingredient> getIngredientByCategoryCategory(String name);

}
