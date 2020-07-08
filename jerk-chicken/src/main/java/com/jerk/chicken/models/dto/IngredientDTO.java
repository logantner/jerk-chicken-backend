package com.jerk.chicken.models.dto;

import java.io.Serializable;

import com.jerk.chicken.models.Category;
import com.jerk.chicken.models.Unit;

public class IngredientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int ingredient_id;
	
	private String name;
	
	private int qty;
	
	private Category category;
	
	private Unit unit;
	
	private IngredientDescriptionDTO ingredientDescription;

	public IngredientDTO() {
		super();
	}

	public IngredientDTO(int ingredient_id) {
		super();
		this.ingredient_id = ingredient_id;
	}

	public int getIngredient_id() {
		return ingredient_id;
	}

	public void setIngredient_id(int ingredient_id) {
		this.ingredient_id = ingredient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public IngredientDescriptionDTO getIngredientDescription() {
		return ingredientDescription;
	}

	public void setIngredientDescription(IngredientDescriptionDTO ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingredient_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + qty;
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
		IngredientDTO other = (IngredientDTO) obj;
		if (ingredient_id != other.ingredient_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (qty != other.qty)
			return false;
		return true;
	}
	
	
	
	
	
}
