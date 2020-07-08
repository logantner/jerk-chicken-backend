package com.jerk.chicken.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.Ingredient;
import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.dto.IngredientBasketDTO;
import com.jerk.chicken.models.dto.SimpleRecipeDTO;
import com.jerk.chicken.repositories.IngredientRepository;
import com.jerk.chicken.repositories.RecipeRepository;
import com.jerk.chicken.services.IngredientService;

@CrossOrigin
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	IngredientService ingredientService;

	@Autowired
	RecipeRepository recipeRepo;
	
	@Autowired
	IngredientRepository ingredientRepo;
	
	/**
	 * <h2>getBasketIngredients
	 * <h2>
	 * <p>
	 * Parses query string, if there is one, and calls the appropriate service
	 * method. Returns a list of IngredientBasketDTO objects.
	 * </p>
	 * 
	 * @endpoint /ingredients/basket
	 * @author James
	 * @param category
	 * @return
	 */
	@GetMapping("/basket")
	public List<IngredientBasketDTO> getBasketIngredients(
			@RequestParam(name = "category", required = false) String category) {
		if (category != null) {
			return ingredientService.getBasketIngredientsByCategory(category);
		}
		return ingredientService.getAllBasketIngredients();
	}
	
	
	// in development
	@PostMapping("/strict-search")
	public List<SimpleRecipeDTO> getSimpleRecipeByIngredientsStrict(@RequestBody List<Integer> ingredientIds){
		List<SimpleRecipeDTO> recipes = null;
		System.out.println(ingredientIds);
		
		List<Ingredient> requestedIngredients = ingredientRepo.findByIdIn(ingredientIds);
		
		for(Ingredient i : requestedIngredients) {
			System.out.println(i.getName());
		}
		
		List<Recipe> recs = recipeRepo.findByRecipeUnitIngredientsUnitIngredientIngredientIdIn(ingredientIds);
		
		System.out.println(recs.get(0).getRecipeUnitIngredients().get(0).getUnitIngredient().getIngredient().getName());
		
		return recipes;
	}
	
	@PostMapping("/search")
	public List<SimpleRecipeDTO> getSimpleRecipeByIngredients(@RequestBody List<Integer> ingredientIds){
		List<SimpleRecipeDTO> recipes = new ArrayList<>();
		List<Recipe> recs = recipeRepo.findByRecipeUnitIngredientsUnitIngredientIngredientIdIn(ingredientIds);
		
		
		for(Recipe r : recs) {
			SimpleRecipeDTO recipe = new SimpleRecipeDTO();
			recipe.setId(r.getId());
			recipe.setName(r.getName());
			if(!recipes.contains(recipe))
				recipes.add(recipe);
		}
		
		return recipes;
	}

}
