package com.jerk.chicken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Category;
import com.jerk.chicken.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	
	public List<Category> getCategories() {
		return categoryRepo.findAll();
		
		
	}
	
	
}
