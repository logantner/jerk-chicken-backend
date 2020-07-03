package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.UserRecipe;

@Repository
public interface UserRecipeRepository extends JpaRepository<UserRecipe,Integer>{

}
