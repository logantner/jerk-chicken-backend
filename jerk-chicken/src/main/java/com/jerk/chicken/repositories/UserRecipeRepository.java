package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jerk.chicken.models.UserRecipe;

@Repository
public interface UserRecipeRepository extends JpaRepository<UserRecipe,Integer>{
	public List<UserRecipe> findByUserId(int id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM user_recipe WHERE recipe_id = ?1", nativeQuery = true)
	void removeByRecipeId(Integer id);
}
