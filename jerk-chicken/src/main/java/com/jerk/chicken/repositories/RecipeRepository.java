package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
}
