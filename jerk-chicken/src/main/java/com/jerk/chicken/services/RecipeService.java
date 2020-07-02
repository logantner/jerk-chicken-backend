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
}
