package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.IngredientDescription;
import com.jerk.chicken.repositories.IngredientDescriptionRepository;

@Service
public class IngredientDescriptionService {
	@Autowired
	IngredientDescriptionRepository idr;
	
	public List<IngredientDescription> getIngredientDescriptions(int id){
		return idr.findByIngredientId(id);
	}
	
	public List<IngredientDescription> getAllDescriptions(){
		return idr.findAll();
	}
}
