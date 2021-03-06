package com.jerk.chicken.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Component
public class IngredientDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ingredient_id")
	@JsonBackReference
	private Ingredient ingredient;
	
	@Column(columnDefinition="TEXT")
	private String description;

	@OneToOne(mappedBy = "ingredientDescription")
	private RecipeUnitIngredient recipeUnitIngredient;
	
	
	public IngredientDescription() {
		super();
	}


	public IngredientDescription(int id, Ingredient ingredient, String description) {
		super();
		this.id = id;
		this.ingredient = ingredient;
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Ingredient getIngredient() {
		return ingredient;
	}


	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
		result = prime * result + id;
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
		IngredientDescription other = (IngredientDescription) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "IngredientDescription [id=" + id + ", ingredient=" + ingredient + ", description=" + description + "]";
	}
	
	
	
}
