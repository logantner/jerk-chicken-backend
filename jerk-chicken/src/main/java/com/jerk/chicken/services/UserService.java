package com.jerk.chicken.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.Role;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.models.UserRole;
import com.jerk.chicken.repositories.UserRecipeRepository;
import com.jerk.chicken.repositories.UserRepository;
import com.jerk.chicken.repositories.UserRoleRepository;
import com.jerk.chicken.util.JwtValidate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService{

	@Autowired
	JwtValidate jwt;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	UserRoleRepository userRoleRepo;
	
	@Autowired
	UserRecipeRepository userRecipeRepo;

	public String login(User user) {
		User u = userRepo.findByUsername(user.getUsername());
		
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			
		if(u == null)
			return String.format("User not found");
		if(!bCrypt.matches(user.getPassword(), u.getPassword()))
			 return String.format("Incorrect Password");
			
		List<UserRole> userroles = userRoleRepo.findByUserId(u.getId());
		List<Role> roles = new ArrayList<>();
				
		for(UserRole userrole : userroles) {
			Role role = new Role();
			role.setId(userrole.getRole().getId());
			role.setRole(userrole.getRole().getRole());
			roles.add(role);
		}
			
		return jwt.generateJWT(roles,u);
	}

	public String parseObject(String s) {
		return s.substring(s.indexOf('=') + 1);
	}

	public void deleteUser(User u) {	
		List<UserRole> userRolesToDelete = userRoleRepo.findByUserId(u.getId());
		for(UserRole userrole : userRolesToDelete) {
			userRoleRepo.deleteById(userrole.getId());
		}
		
		userRepo.delete(u);
	}

	public User registerUser(User u) {
		return userRepo.save(u);
	}

	public Recipe addRecipeToRecipeBook(Recipe r, User u) {
		return userRecipeRepo.save(new UserRecipe(0, u, r)).getRecipe();
	}
	
	public List<Recipe> getUserRecipes(int userId){
		List<UserRecipe> userrecipes = userRecipeRepo.findByUserId(userId);
		List<Recipe> recipes = new ArrayList<>();
		for(UserRecipe u : userrecipes) {
			recipes.add(u.getRecipe());
		}
		return recipes;
	}
}
