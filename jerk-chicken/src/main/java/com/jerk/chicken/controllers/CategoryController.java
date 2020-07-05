package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.Category;
import com.jerk.chicken.services.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<Category> getCategories(){
		return categoryService.getCategories();
	}
	
	
	
}
