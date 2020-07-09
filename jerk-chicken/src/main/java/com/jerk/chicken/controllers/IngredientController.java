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
	public List<IngredientBasketDTO> getBasketIngredients(@RequestParam(name = "category", required = false) String category) {
		if (category != null) {
			return ingredientService.getBasketIngredientsByCategory(category);
		}
		return ingredientService.getAllBasketIngredients();
	}
	
	@PostMapping("/strict-search")
	public List<SimpleRecipeDTO> getSimpleRecipeByIngredientsStrict(@RequestBody List<Integer> ingredientIds){
		return ingredientService.strictsearch(ingredientIds);
	}
	
	@PostMapping("/search")
	public List<SimpleRecipeDTO> getSimpleRecipeByIngredients(@RequestBody List<Integer> ingredientIds){
		return ingredientService.search(ingredientIds);
	}

}
