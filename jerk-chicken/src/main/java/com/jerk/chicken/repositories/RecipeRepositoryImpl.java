package com.jerk.chicken.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jerk.chicken.models.Recipe;

public class RecipeRepositoryImpl implements RecipeCustomRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Recipe> populateOwnerRecipes(int id) {
		Recipe recipe = new Recipe();
		String recipeHQL = "SELECT r from recipe r where owner = ?1 ";
		
		
		
		Query query = em.createQuery(recipeHQL);
		query.setParameter(1, 1);
		
	
		List<Recipe> recipes = query.getResultList();
	
		System.out.println(recipes);
		
		return recipes;
	}

}
