package com.jerk.chicken.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
public class Recipe implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@Column(name="owner_id", nullable=true)
	private Integer owner;
	
	private String name;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy="recipe", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Step> steps;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy="recipe", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<RecipeUnitIngredient> recipeUnitIngredients;
	
	private Integer prepTime;
	
	private Integer cookTime;

	public Recipe() {
		super();
	}

	public Recipe(int id, int owner, String name, List<Step> steps, List<RecipeUnitIngredient> recipeUnitIngredients, int prepTime, int cookTime) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.steps = steps;
		this.recipeUnitIngredients = recipeUnitIngredients;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public List<RecipeUnitIngredient> getRecipeUnitIngredients() {
		return recipeUnitIngredients;
	}

	public void setRecipeUnitIngredients(List<RecipeUnitIngredient> recipeUnitIngredients) {
		this.recipeUnitIngredients = recipeUnitIngredients;
	}
	
	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
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
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", owner=" + owner + ", name=" + name + ", steps=" + steps
				+ ", recipeUnitIngredients=" + recipeUnitIngredients + ", prepTime=" + prepTime + ", cookTime="
				+ cookTime + "]";
	}

	
}
