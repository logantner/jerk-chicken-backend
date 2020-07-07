package com.jerk.chicken.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Component
public class Step implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@JsonBackReference
	private Recipe recipe;
	
	private int position;
	
	private String instruction;

	public Step() {
		super();
	}

	public Step(int id, Recipe recipe, int position, String instruction) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.position = position;
		this.instruction = instruction;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
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
		Step other = (Step) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Step [id=" + id + ", recipe=" + recipe + ", position=" + position + ", instruction=" + instruction
				+ "]";
	}
	
	
	
	
	
}
