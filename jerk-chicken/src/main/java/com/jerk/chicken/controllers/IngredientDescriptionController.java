package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.IngredientDescription;
import com.jerk.chicken.services.IngredientDescriptionService;

@CrossOrigin
@RestController
@RequestMapping("/descriptions")
public class IngredientDescriptionController {
	@Autowired
	IngredientDescriptionService ids;
	
	@GetMapping
	public List<IngredientDescription> getIngredientDescriptions(@RequestParam(name="ingredient", required=false) String id){
		if(id!=null) {
			int ingredientId = Integer.parseInt(id);
			return ids.getIngredientDescriptions(ingredientId);
		}
		return ids.getAllDescriptions();
	}
}
