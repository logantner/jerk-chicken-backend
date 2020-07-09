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
import com.jerk.chicken.models.dto.SimpleRecipeDTO;
import com.jerk.chicken.services.UserService;
import com.jerk.chicken.util.JwtValidate;
import com.jerk.chicken.util.JwtValidate.UserData;

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
	public String registerUser(@RequestBody User u) {
		return us.registerUser(u);
	}
	
	@DeleteMapping
	public ResponseEntity deleteUser(@RequestBody User u, @RequestHeader("x-access-token") String token) {
		UserData user = jwt.getUserData(token);
		if(user.getRoles().contains(new Role(1,"admin"))){
			us.deleteUser(u);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("/recipebook")
	public ResponseEntity<Recipe> addRecipeToRecipeBook(@RequestBody Recipe recipe, @RequestHeader("x-access-token") String token) {
		UserData user = jwt.getUserData(token);
		if(user.getRoles().contains(new Role(2,"user"))) {
			return new ResponseEntity<>(us.addRecipeToRecipeBook(recipe, user.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/recipebook")
	public ResponseEntity<List<Recipe>> getUserRecipeBook(@RequestHeader("x-access-token") String token){
		UserData user = jwt.getUserData(token);
		if(user.getRoles().contains(new Role(2,"user"))) {
			return new ResponseEntity<>(us.getUserRecipeBook(user.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/recipes")
	public ResponseEntity<List<SimpleRecipeDTO>> getUserRecipes(@RequestHeader("x-access-token")String token){
		UserData user = jwt.getUserData(token);
		if(user.getRoles().contains(new Role(2,"user"))) {
			return new ResponseEntity<>(us.getUserRecipes(user.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
