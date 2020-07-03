package com.jerk.chicken.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Ingredient;
import com.jerk.chicken.models.dto.IngredientBasketDTO;
import com.jerk.chicken.repositories.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	IngredientRepository ingredientRepository;

	/**
	 * <h2>getAllBasketIngredients
	 * <h2>
	 *
	 * <p>
	 * gets all the ingredients from the database. If any ingredients are return
	 * they are passed for conversion to DTOs. If not records are returned an empty
	 * list is returned
	 * </p>
	 * 
	 * @author James
	 * @return List<IngredientBasketDTO>
	 */
	public List<IngredientBasketDTO> getAllBasketIngredients() {

		List<Ingredient> ingredients = ingredientRepository.findAll();
		if (ingredients.size() > 0) {
			return convertIngredientsToDTO(ingredients);
		}
		return new ArrayList<IngredientBasketDTO>();

	}

	/**
	 * <h2>getBasketIngredientsByCategory
	 * <h2>
	 *
	 * <p>
	 * gets all the ingredients from the database based on the category name. The
	 * category is parsed to the proper format enabling the query string to handle
	 * any case. If any ingredients are return they are passed for conversion to
	 * DTOs. If not records are returned an empty list is returned
	 * </p>
	 * 
	 * @author James
	 * @return List<IngredientBasketDTO>
	 */
	public List<IngredientBasketDTO> getBasketIngredientsByCategory(String category) {

		String parsedCategory = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();

		List<Ingredient> ingredients = ingredientRepository.getIngredientByCategoryCategory(parsedCategory);
		if (ingredients.size() > 0) {
			return convertIngredientsToDTO(ingredients);
		}

		return new ArrayList<IngredientBasketDTO>();
	}

	/**
	 * 
	 * <h2>convertIngredientToDTO</h2>
	 * <p>
	 * Method takes ingredient records and flattens nested resources into a single
	 * object for ease of access on the front end. Ingredient descriptions are not
	 * needed for display on the public ingredient basket so the descriptions are
	 * ignored.
	 * </p>
	 * 
	 * @author James
	 * @private
	 * @param List<Ingredient> ingredientsToConvert
	 * @return List<IngredientBasketDTO>
	 */
	private List<IngredientBasketDTO> convertIngredientsToDTO(List<Ingredient> ingredientsToConvert) {
		List<IngredientBasketDTO> ingredients = new ArrayList<>();

		// converts to flattened DTO for a simplified package to be sent to the
		// requester
		for (Ingredient ingredient : ingredientsToConvert) {
			IngredientBasketDTO dto = new IngredientBasketDTO();
			dto.setCategory(ingredient.getCategory().getCategory());
			dto.setCategory_id(ingredient.getCategory().getId());
			dto.setId(ingredient.getId());
			dto.setName(ingredient.getName());

			ingredients.add(dto);
		}

		return ingredients;
	}

}
