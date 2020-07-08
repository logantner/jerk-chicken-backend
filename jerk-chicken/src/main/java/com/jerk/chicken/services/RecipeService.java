package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.repositories.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	RecipeRepository rr;
	
	public List<Recipe> getAllRecipes(){
		return rr.findAll();
	}
	
	public Recipe getRecipeById(int id) {
		return rr.findById(id);
	}
	
	public Recipe addRecipe(Recipe r) {
		return rr.save(r);
	}
	
	public void deleteRecipe(Recipe r) {
		rr.delete(r);
	}
	
	public Recipe updateRecipe(Recipe r) {
		return rr.save(r);
	}
}
