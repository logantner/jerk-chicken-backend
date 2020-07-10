package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jerk.chicken.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	public List<Recipe> findByOwner(int id);
	public Recipe findById(int id);
	List<Recipe> findByRecipeUnitIngredientsUnitIngredientIngredientId(int id);
	List<Recipe> findByRecipeUnitIngredientsUnitIngredientIngredientIdIn(List<Integer> ids);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM recipe WHERE Id = ?1", nativeQuery = true)
	void deleteByRecipeId(int id);
}
