package com.jerk.chicken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.Role;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.services.UserService;
import com.jerk.chicken.util.JwtValidate;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService us;
	@Autowired
	JwtValidate jwt;
	

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return us.login(user);
	}
	

	@PostMapping("/register")
	public User registerUser(@RequestBody User u) {
		return us.registerUser(u);
	}
	

	@DeleteMapping("/")
	public void deleteUser(@RequestBody User u) {
		//ADMIN CHECK
		us.deleteUser(u);
	}
	
	@PostMapping("/recipes")
	public Recipe addRecipeToRecipeBook(@RequestBody UserRecipe ur) {
		//USER CHECK
		User u = ur.getUser();
		Recipe r = ur.getRecipe();
		return us.addRecipeToRecipeBook(r, u);
	}
	
	@GetMapping("/recipes")
	public ResponseEntity<List<Recipe>> getUserRecipes(@RequestHeader("x-access-token") String token){
		//USER CHECK
		int userId = (int) jwt.validateJwt(token, "id");
		List<Role> roles = jwt.getRoles(jwt.validateJwt(token, "roles"));
		for(Role r : roles) {
			if(r.getRole().equals("user")) {
				return new ResponseEntity<>(us.getUserRecipes(userId), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
