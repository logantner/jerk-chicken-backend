package com.jerk.chicken.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
public class RecipeUnitIngredient implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@JsonBackReference
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="unit_ingredient_id")
	@JsonBackReference
	private UnitIngredient unitIngredient;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "ingredient_description_id", referencedColumnName="id")
	private IngredientDescription ingredientDescription;
	
	private int qty;

	public RecipeUnitIngredient() {
		super();
	}

	public RecipeUnitIngredient(int id, Recipe recipe, UnitIngredient unitIngredient, int qty) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.unitIngredient = unitIngredient;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public UnitIngredient getUnitIngredient() {
		return unitIngredient;
	}

	public void setUnitIngredient(UnitIngredient unitIngredient) {
		this.unitIngredient = unitIngredient;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public IngredientDescription getIngredientDescription() {
		return ingredientDescription;
	}

	public void setIngredientDescription(IngredientDescription ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}

	@Override
	public String toString() {
		return "RecipeUnitIngredient [id=" + id + ", recipe=" + recipe + ", unitIngredient=" + unitIngredient + ", qty="
				+ qty + "]";
	}
	
	
}
