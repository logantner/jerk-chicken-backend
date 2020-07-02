package com.jerk.chicken.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;


@Entity
@Component
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(mappedBy="ingredient", fetch = FetchType.EAGER)
	private List<IngredientDescription> descriptions;

	public Ingredient() {
		super();
	}

	public Ingredient(int id, String name, Category category, List<IngredientDescription> descriptions) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.descriptions = descriptions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<IngredientDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<IngredientDescription> descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", category=" + category + ", descriptions=" + descriptions
				+ "]";
	}
	
	
	

}
