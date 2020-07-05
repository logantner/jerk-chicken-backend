package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.services.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
	
	
	@Autowired
	RecipeService rs;
	
	@GetMapping("/")
	@ResponseBody
	public List<Recipe> getAllRecipes(){
		return rs.getAllRecipes();
	}
	
	@PostMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe addRecipe(@RequestBody Recipe r) {
		return rs.addRecipe(r);
	}
	
	@DeleteMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@RequestBody Recipe r) {
		rs.deleteRecipe(r);
	}
	
	@PutMapping("/")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Recipe updateRecipe(@RequestBody Recipe r) {
		return rs.updateRecipe(r);
	}
}
