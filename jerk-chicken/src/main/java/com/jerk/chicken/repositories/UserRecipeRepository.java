package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.UserRecipe;

@Repository
public interface UserRecipeRepository extends JpaRepository<UserRecipe,Integer>{
	public List<UserRecipe> findByUserId(int id);
}
