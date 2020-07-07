package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.services.RecipeService;

@CrossOrigin
@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	
	@Autowired
	RecipeService rs;
	
	@GetMapping
	public List<Recipe> getAllRecipes(){
		return rs.getAllRecipes();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe addRecipe(@RequestBody Recipe r) {
		return rs.addRecipe(r);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@RequestBody Recipe r) {
		rs.deleteRecipe(r);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Recipe updateRecipe(@RequestBody Recipe r) {
		return rs.updateRecipe(r);
	}
}
