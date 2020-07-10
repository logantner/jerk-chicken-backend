package com.jerk.chicken.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplexRecipeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int recipe_id;
	
	private String name;
	
	private int cookTime;
	
	private int prepTime;
	
	private int ownerId;

	private List<StepDTO> steps;
	
	private List<IngredientDTO> ingredients;

	public ComplexRecipeDTO() {
		super();
		steps = new ArrayList<>();
		ingredients = new ArrayList<>();
	}

	public ComplexRecipeDTO(int recipe_id) {
		super();
		this.recipe_id = recipe_id;
		steps = new ArrayList<>();
		ingredients = new ArrayList<>();
	}
	
	public void addStep(StepDTO dto) {
		steps.add(dto);
	}
	
	public void addIngredient(IngredientDTO dto) {
		ingredients.add(dto);
	}

	public int getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public List<StepDTO> getSteps() {
		return steps;
	}

	public void setSteps(List<StepDTO> steps) {
		this.steps = steps;
	}

	public List<IngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}
	
	

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cookTime;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + prepTime;
		result = prime * result + recipe_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexRecipeDTO other = (ComplexRecipeDTO) obj;
		if (cookTime != other.cookTime)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prepTime != other.prepTime)
			return false;
		if (recipe_id != other.recipe_id)
			return false;
		return true;
	}
	
	
	
}
