package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.Category;
import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.RecipeUnitIngredient;
import com.jerk.chicken.models.Step;
import com.jerk.chicken.models.Unit;
import com.jerk.chicken.models.dto.ComplexRecipeDTO;
import com.jerk.chicken.models.dto.IngredientDTO;
import com.jerk.chicken.models.dto.IngredientDescriptionDTO;
import com.jerk.chicken.models.dto.StepDTO;
import com.jerk.chicken.repositories.RecipeRepository;
import com.jerk.chicken.services.RecipeService;

@CrossOrigin
@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	RecipeService rs;

	@Autowired
	RecipeRepository recipeRepo;

	@GetMapping
	public List<Recipe> getAllRecipes() {
		return rs.getAllRecipes();
	}

	@GetMapping("/{id}")
	public ComplexRecipeDTO getRecipeById(@PathVariable("id") int id) {
		return rs.getRecipeById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComplexRecipeDTO addRecipe(@RequestBody ComplexRecipeDTO r) {
		return rs.saveRecipe(r,1);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@RequestBody Recipe r) {
		rs.deleteRecipe(r);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ComplexRecipeDTO updateRecipe(@RequestBody ComplexRecipeDTO r) {
		return rs.saveRecipe(r,1);
	}
}
