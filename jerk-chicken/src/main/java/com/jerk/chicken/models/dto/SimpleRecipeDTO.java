package com.jerk.chicken.models.dto;

import java.io.Serializable;

public class SimpleRecipeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	public SimpleRecipeDTO() {
		super();
	}
	public SimpleRecipeDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		SimpleRecipeDTO other = (SimpleRecipeDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
