package com.jerk.chicken.models.dto;

import java.io.Serializable;

public class IngredientBasketDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int category_id;
	private String category;
	public IngredientBasketDTO() {
		super();
	}
	
	
	public IngredientBasketDTO(int id, String name, int category_id, String category) {
		super();
		this.id = id;
		this.name = name;
		this.category_id = category_id;
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
		IngredientBasketDTO other = (IngredientBasketDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IngredientBasketDTO [id=" + id + ", name=" + name + ", category=" + category + "]";
	}
}
