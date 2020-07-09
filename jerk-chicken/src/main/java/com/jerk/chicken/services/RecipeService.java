package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class RecipeService {
	@Autowired
	RecipeRepository rr;
	
	public List<Recipe> getAllRecipes(){
		return rr.findAll();
	}
	
	public ComplexRecipeDTO getRecipeById(int id) {
		ComplexRecipeDTO dto = new ComplexRecipeDTO();
		Recipe recipe = rr.getOne(id);

		dto.setRecipe_id(recipe.getId());
		dto.setName(recipe.getName());
		dto.setCookTime(recipe.getCookTime());
		dto.setPrepTime(recipe.getPrepTime());

		if (recipe.getSteps() != null) {
			for (Step step : recipe.getSteps()) {
				StepDTO stepDTO = new StepDTO();
				stepDTO.setStep_id(step.getId());
				stepDTO.setPosition(step.getPosition());
				stepDTO.setInstruction(step.getInstruction());
				dto.addStep(stepDTO);
			}
		}

		if (recipe.getRecipeUnitIngredients() != null) {
			for (RecipeUnitIngredient unitIngredient : recipe.getRecipeUnitIngredients()) {
				IngredientDTO ingredientDTO = new IngredientDTO();
				ingredientDTO.setQty(unitIngredient.getQty());
				ingredientDTO.setIngredient_id(unitIngredient.getUnitIngredient().getIngredient().getId());
				ingredientDTO.setName(unitIngredient.getUnitIngredient().getIngredient().getName());

				if (unitIngredient.getUnitIngredient().getIngredient().getCategory() != null) {
					Category category = new Category();
					category.setId(unitIngredient.getUnitIngredient().getIngredient().getCategory().getId());
					category.setCategory(
							unitIngredient.getUnitIngredient().getIngredient().getCategory().getCategory());
					ingredientDTO.setCategory(category);
				}

				if (unitIngredient.getUnitIngredient().getUnit() != null) {
					Unit unit = new Unit();
					unit.setId(unitIngredient.getUnitIngredient().getUnit().getId());
					unit.setLongType(unitIngredient.getUnitIngredient().getUnit().getLongType());
					unit.setShortType(unitIngredient.getUnitIngredient().getUnit().getShortType());
					ingredientDTO.setUnit(unit);
				}

				if (unitIngredient.getIngredientDescription() != null) {
					IngredientDescriptionDTO descriptionDTO = new IngredientDescriptionDTO();
					descriptionDTO.setIngredient_description_id(unitIngredient.getIngredientDescription().getId());
					descriptionDTO.setDescription(unitIngredient.getIngredientDescription().getDescription());
					ingredientDTO.setIngredientDescription(descriptionDTO);
				}

				dto.addIngredient(ingredientDTO);
			}
		}
		return dto;
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
