package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jerk.chicken.models.Step;

@Repository
public interface StepRepository extends JpaRepository<Step,Integer> {
	
	@Transactional
	@Modifying
	@Query(value= "DELETE FROM step where recipe_id = ?1", nativeQuery = true)
	void removeByRecipeId(Integer id);

}
