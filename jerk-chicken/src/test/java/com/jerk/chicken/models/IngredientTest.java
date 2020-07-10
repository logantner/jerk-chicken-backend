package com.jerk.chicken.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IngredientTest {
	
	Ingredient i;

	@Before
	public void setUp() {
		List<IngredientDescription> desc = new ArrayList<>();
		i = new Ingredient(1,"Potato", new Category(1, "Starch"), null);
		desc.add(new IngredientDescription(1,i,"Russett Potato"));
		desc.add(new IngredientDescription(2,i,"Yukon Gold Potato"));
		i.setDescriptions(desc);
	}
	
	@Test
	public void testGetId() {
		assertEquals(i.getId(),1);
	}
	
	@Test
	public void testSetId() {
		i.setId(2);
		assertEquals(i.getId(),2);
	}
	
	@Test
	public void testGetName() {
		assertEquals(i.getName(), "Potato");
	}
	
	@Test
	public void testSetName() {
		i.setName("Potat");
		assertEquals(i.getName(), "Potat");
	}
	
	@Test
	public void testGetCategory() {
		assertEquals(i.getCategory(), new Category(1,"Starch"));
	}
	
	@Test
	public void testSetCategory() {
		Category c = new Category(2,"Vegetable");
		i.setCategory(c);
		assertEquals(i.getCategory(), new Category(2,"Vegetable"));
	}
	
	@Test
	public void testGetDescriptions() {
		List<IngredientDescription> desc = i.getDescriptions();
		assertEquals(desc.get(0).getDescription(), "Russett Potato");
	}
	
	@Test
	public void testSetDescriptions() {
		List<IngredientDescription> desc = new ArrayList<>();
		desc.add(new IngredientDescription(3,i,"Purple Peruvian Potato"));
		i.setDescriptions(desc);
		assertEquals(i.getDescriptions().get(0).getDescription(), "Purple Peruvian Potato");
	}
}
