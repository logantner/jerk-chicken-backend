package com.jerk.chicken.models.dto;

import java.io.Serializable;

public class IngredientDescriptionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int ingredient_description_id;
	
	private String description;

	public IngredientDescriptionDTO() {
		super();
	}

	public IngredientDescriptionDTO(int ingredient_description_id) {
		super();
		this.ingredient_description_id = ingredient_description_id;
	}

	public IngredientDescriptionDTO(int ingredient_description_id, String description) {
		super();
		this.ingredient_description_id = ingredient_description_id;
		this.description = description;
	}

	public int getIngredient_description_id() {
		return ingredient_description_id;
	}

	public void setIngredient_description_id(int ingredient_description_id) {
		this.ingredient_description_id = ingredient_description_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ingredient_description_id;
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
		IngredientDescriptionDTO other = (IngredientDescriptionDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ingredient_description_id != other.ingredient_description_id)
			return false;
		return true;
	}
	
	
	

}
