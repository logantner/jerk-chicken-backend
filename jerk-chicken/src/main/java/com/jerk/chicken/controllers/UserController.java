package com.jerk.chicken.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.services.UserService;

@Controller
@CrossOrigin("http://76.98.248.124:3000")
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService us;
	

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody User user) {
		return us.login(user.getUsername());
	}
	

	@PostMapping("/register")
	@ResponseBody
	public User registerUser(@RequestBody User u) {
		return us.registerUser(u);
	}
	

	@DeleteMapping("/")
	@ResponseBody
	public void deleteUser(@RequestBody User u) {
		us.deleteUser(u);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Recipe addRecipeToRecipeBook(@RequestBody UserRecipe ur) {
		User u = ur.getUser();
		Recipe r = ur.getRecipe();
		return us.addRecipeToRecipeBook(r, u);
	}
}
