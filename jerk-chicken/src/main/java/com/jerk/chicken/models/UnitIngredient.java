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
public class UnitIngredient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="unit_id")
	@JsonBackReference
	private Unit unit;
	
	@ManyToOne
	@JoinColumn(name="ingredient_id")
	@JsonBackReference
	private Ingredient ingredient;

	public UnitIngredient() {
		super();
	}

	public UnitIngredient(int id, Unit unit, Ingredient ingredient) {
		super();
		this.id = id;
		this.unit = unit;
		this.ingredient = ingredient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
		UnitIngredient other = (UnitIngredient) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnitIngredient [id=" + id + ", unit=" + unit + ", ingredient=" + ingredient + "]";
	}
	
	

}
