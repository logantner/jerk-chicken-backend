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
	public List<Recipe> getAllRecipes(){
		return rs.getAllRecipes();
	}
	
	

	@GetMapping("/{id}")
	public ComplexRecipeDTO getRecipeById(@PathVariable("id") int id) {
		ComplexRecipeDTO dto = new ComplexRecipeDTO();
		Recipe recipe = recipeRepo.getOne(id);
		
		dto.setRecipe_id(recipe.getId());
		dto.setName(recipe.getName());
		dto.setCookTime(recipe.getCookTime());
		dto.setPrepTime(recipe.getPrepTime());
		
		if(recipe.getSteps() != null) {
			for(Step step : recipe.getSteps()) {
				StepDTO stepDTO = new StepDTO();
				stepDTO.setStep_id(step.getId());
				stepDTO.setPosition(step.getPosition());
				stepDTO.setInstruction(step.getInstruction());
				dto.addStep(stepDTO);
			}
		}
		
		if(recipe.getRecipeUnitIngredients() != null) {
			for(RecipeUnitIngredient unitIngredient : recipe.getRecipeUnitIngredients()) {
				IngredientDTO ingredientDTO = new IngredientDTO();
				ingredientDTO.setQty(unitIngredient.getQty());
				ingredientDTO.setIngredient_id(unitIngredient.getUnitIngredient().getIngredient().getId());
				ingredientDTO.setName(unitIngredient.getUnitIngredient().getIngredient().getName());
				
				if(unitIngredient.getUnitIngredient().getIngredient().getCategory() != null) {
					Category category = new Category();
					category.setId(unitIngredient.getUnitIngredient().getIngredient().getCategory().getId());
					category.setCategory(unitIngredient.getUnitIngredient().getIngredient().getCategory().getCategory());
					ingredientDTO.setCategory(category);
				}
				
				if(unitIngredient.getUnitIngredient().getUnit() != null) {
					Unit unit = new Unit();
					unit.setId(unitIngredient.getUnitIngredient().getUnit().getId());
					unit.setLongType(unitIngredient.getUnitIngredient().getUnit().getLongType());
					unit.setShortType(unitIngredient.getUnitIngredient().getUnit().getShortType());
					ingredientDTO.setUnit(unit);
				}
			
				
				if(unitIngredient.getIngredientDescription() != null) {
					IngredientDescriptionDTO descriptionDTO = new IngredientDescriptionDTO();
					descriptionDTO.setIngredient_description_id(unitIngredient.getIngredientDescription().getId());
					descriptionDTO.setDescription(unitIngredient.getIngredientDescription().getDescription());
					ingredientDTO.setIngredientDescription(descriptionDTO);
				}
				
				
				
				
				dto.addIngredient(ingredientDTO);
		}
		
			
			
		}
		
		//System.out.println(recipe.getRecipeUnitIngredients().get(1).getUnitIngredient().getUnit().getLongType());
		
		
		return dto;
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
