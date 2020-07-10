package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Category;
import com.jerk.chicken.models.Ingredient;
import com.jerk.chicken.models.IngredientDescription;
import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.RecipeUnitIngredient;
import com.jerk.chicken.models.Step;
import com.jerk.chicken.models.Unit;
import com.jerk.chicken.models.UnitIngredient;
import com.jerk.chicken.models.dto.ComplexRecipeDTO;
import com.jerk.chicken.models.dto.IngredientDTO;
import com.jerk.chicken.models.dto.IngredientDescriptionDTO;
import com.jerk.chicken.models.dto.StepDTO;
import com.jerk.chicken.repositories.RecipeRepository;
import com.jerk.chicken.repositories.RecipeUnitIngredientRepository;
import com.jerk.chicken.repositories.StepRepository;
import com.jerk.chicken.repositories.UnitIngredientRepository;
import com.jerk.chicken.repositories.UserRecipeRepository;

@Service
public class RecipeService {
	@Autowired
	RecipeRepository rr;
	
	@Autowired
	StepRepository stepRepo;
	
	@Autowired
	RecipeUnitIngredientRepository recipeUnitIngredientRepo;
	
	@Autowired
	UnitIngredientRepository unitIngredientRepo;
	
	@Autowired
	UserRecipeRepository userRecipeRepo;
	
		
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
	
	
	// need to pass userId
	public ComplexRecipeDTO saveRecipe(ComplexRecipeDTO r, int userId) {
		Recipe recipe = new Recipe();
		recipe.setName(r.getName());
		recipe.setOwner(userId);
		recipe.setPrepTime(r.getPrepTime());
		recipe.setCookTime(r.getCookTime());
		
		if(r.getRecipe_id() > 0) {
			recipe.setId(r.getRecipe_id());
		}
		recipe.setId(rr.save(recipe).getId());
		r.setRecipe_id(recipe.getId());
		
		for(int x = 0; x < r.getSteps().size(); x++) {
			StepDTO step = r.getSteps().get(x);
			Step newStep = new Step();
			
			if(step.getStep_id() > 0) {
				newStep.setId(step.getStep_id());
			}
			
			newStep.setInstruction(step.getInstruction());
			newStep.setPosition(step.getPosition());
			newStep.setRecipe(recipe);
			
			step.setStep_id(stepRepo.save(newStep).getId());		
		}
		
		for(int x = 0; x < r.getIngredients().size(); x++) {
			IngredientDTO ingredientDTO = r.getIngredients().get(x);
			
			
			UnitIngredient unitIngredient = unitIngredientRepo.findByUnitIdAndIngredientId(ingredientDTO.getUnit().getId(), ingredientDTO.getIngredient_id());
			
			if(unitIngredient == null) {
				unitIngredient = new UnitIngredient();
				Ingredient ingredient = new Ingredient();
				ingredient.setId(ingredientDTO.getIngredient_id());
				
				Unit unit = new Unit();
				unit.setId(ingredientDTO.getUnit().getId());
				
				unitIngredient.setIngredient(ingredient);
				unitIngredient.setUnit(unit);
				
				unitIngredient = unitIngredientRepo.save(unitIngredient);
				
			}
			
			
			RecipeUnitIngredient recipeUnitIngredient = new RecipeUnitIngredient();
			recipeUnitIngredient.setQty(ingredientDTO.getQty());
			recipeUnitIngredient.setRecipe(recipe);
			
			if(ingredientDTO.getIngredientDescription() != null) {
				IngredientDescription ingredientDescription = new IngredientDescription();
				ingredientDescription.setId(ingredientDTO.getIngredientDescription().getIngredient_description_id());
				ingredientDescription.setDescription(ingredientDTO.getIngredientDescription().getDescription());
				recipeUnitIngredient.setIngredientDescription(ingredientDescription);
			}
			
			recipeUnitIngredient.setUnitIngredient(unitIngredient);
			
			recipeUnitIngredient.setId(recipeUnitIngredientRepo.save(recipeUnitIngredient).getId());
				
		}
			
		return r;

	}
	
	public void deleteRecipe(Recipe r) {
		stepRepo.removeByRecipeId(r.getId());
		userRecipeRepo.removeByRecipeId(r.getId());
		recipeUnitIngredientRepo.removeByRecipeId(r.getId());
		rr.deleteByRecipeId(r.getId());
	}
}
