package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.services.RecipeService;

@Controller
public class RecipeController {
	@Autowired
	RecipeService rs;
	
	@GetMapping("/recipes")
	@ResponseBody
	public List<Recipe> getAllRecipes(){
		return rs.getAllRecipes();
	}
}
