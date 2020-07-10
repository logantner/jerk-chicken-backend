package com.jerk.chicken.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RecipeTest {
	
	Recipe r;
	
	@Before
	public void setUp() {
		List<Step> steps = new ArrayList<>();
		List<RecipeUnitIngredient> ingredients = new ArrayList<>();
		r = new Recipe(1, 1, "Salsa Picante", null, null, 15, 0);
		
		steps.add(new Step(1,r,1,"Dice tomatoes"));
		steps.add(new Step(2,r,2,"Add spices"));
		
		ingredients.add(new RecipeUnitIngredient(1,r,new UnitIngredient(1, new Unit(1, "cup", "cup"), new Ingredient(1, "Tomato", new Category(1, "Fruit"), null)), 1));
		ingredients.add(new RecipeUnitIngredient(2,r,new UnitIngredient(2, new Unit(2, "tbsp", "tablespoon"), new Ingredient(2, "Spices", new Category(2, "Other"), null)), 2));

		r.setRecipeUnitIngredients(ingredients);
		r.setSteps(steps);
	}
	
	@Test
	public void testGetId() {
		assertEquals(r.getId(),1);
	}
	
	@Test
	public void testSetId() {
		r.setId(2);
		assertEquals(r.getId(),2);
	}
	
	@Test
	public void testGetOwner() {
		assertEquals(r.getOwner(),1);
	}
	
	@Test
	public void testSetOwner() {
		r.setOwner(2);
		assertEquals(r.getOwner(),2);
	}
	
	@Test
	public void testGetSteps() {
		assertNotNull(r.getSteps());
	}
	
	@Test
	public void testSetSteps() {
		List<Step> steps = new ArrayList<>();
		steps.add(new Step(1,r,1,"Slice tomatoes"));
		r.setSteps(steps);
		assertEquals(r.getSteps().get(0).getInstruction(),"Slice tomatoes");
	}
	
	@Test
	public void testGetPrepTime() {
		assertEquals(r.getPrepTime(),15);
	}
	
	@Test
	public void testGetCookTime() {
		assertEquals(r.getCookTime(),0);
	}
	
	@Test
	public void testSetPrepTime() {
		r.setPrepTime(20);
		assertEquals(r.getPrepTime(),20);
	}
	
	@Test
	public void testSetCookTime() {
		r.setCookTime(5);
		assertEquals(r.getCookTime(),5);
	}
}
