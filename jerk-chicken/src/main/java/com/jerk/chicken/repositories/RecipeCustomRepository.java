package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Recipe;

@Repository
public interface RecipeCustomRepository {
	List<Recipe> populateOwnerRecipes(int id);
}
