package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.dto.IngredientBasketDTO;
import com.jerk.chicken.services.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	IngredientService ingredientService;

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

}
