package com.jerk.chicken.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Ingredient;
import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.RecipeUnitIngredient;
import com.jerk.chicken.models.Role;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.models.UserRole;
import com.jerk.chicken.models.dto.SimpleRecipeDTO;
import com.jerk.chicken.repositories.IngredientRepository;
import com.jerk.chicken.repositories.RecipeRepository;
import com.jerk.chicken.repositories.RoleRepository;
import com.jerk.chicken.repositories.UserRecipeRepository;
import com.jerk.chicken.repositories.UserRepository;
import com.jerk.chicken.repositories.UserRoleRepository;
import com.jerk.chicken.util.JwtValidate;

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
	@Autowired
	RoleRepository roleRepo; 
	@Autowired
	RecipeRepository recipeRepo;
	@Autowired
	IngredientRepository ingredientRepo;

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

	public String registerUser(User u) {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		u.setPassword(bCrypt.encode(u.getPassword()));
		u.setId(userRepo.save(u).getId());
		
		
		Role role = roleRepo.findByRole("user");
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(u);
		userRole.setActive(true);
		userRoleRepo.save(userRole);
		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		String token = jwt.generateJWT(roles, u);
		
		return token;
	}

	public boolean addRecipeToRecipeBook(Recipe r, int u) {
		User newUser = new User(u, "", "");
		
		UserRecipe userRecipe = userRecipeRepo.save(new UserRecipe(0, newUser, r));
		if(userRecipe.getId() > 0)
			return true;	
		return false;
		
	}
	
	public List<SimpleRecipeDTO> getUserRecipeBook(int userId){
		List<UserRecipe> userrecipes = userRecipeRepo.findByUserId(userId);
		List<SimpleRecipeDTO> recipes = new ArrayList<>();
		for(UserRecipe u : userrecipes) {
			SimpleRecipeDTO dto = new SimpleRecipeDTO();
			dto.setId(u.getRecipe().getId());
			dto.setName(u.getRecipe().getName());
			recipes.add(dto);
		}
		return recipes;
	}
	
	public List<SimpleRecipeDTO> getUserRecipes(int userId){
			
		List<SimpleRecipeDTO> userRecipes = new ArrayList<>();
		List<Recipe> recipes = recipeRepo.findByOwner(userId);
		
		for(Recipe r : recipes) {
			SimpleRecipeDTO recipeDTO = new SimpleRecipeDTO();
			
			recipeDTO.setId(r.getId());
			recipeDTO.setName(r.getName());
			
			userRecipes.add(recipeDTO);
		}
			
		return userRecipes;
	}
	
	public List<SimpleRecipeDTO> getUserRecipesByIngredientSearch(int userId, List<Integer> ingredientIds){
		List<SimpleRecipeDTO> recipes = new ArrayList<>();
		
		List<UserRecipe> userRecipes = userRecipeRepo.findByUserId(userId);
		
		for(UserRecipe r : userRecipes) {
			for(RecipeUnitIngredient rui : r.getRecipe().getRecipeUnitIngredients()){
				if(ingredientIds.contains(rui.getUnitIngredient().getIngredient().getId())) {
					SimpleRecipeDTO dto = new SimpleRecipeDTO();
					dto.setId(r.getRecipe().getId());
					dto.setName(r.getRecipe().getName());
					if(!recipes.contains(dto)) {
						recipes.add(dto);	
					}
					break;
				}
				
			}
		}		
		return recipes;	
	}
	
	public List<SimpleRecipeDTO> getUserRecipesByIngredientStrictSearch(int userId, List<Integer> ingredientIds){
		List<SimpleRecipeDTO> recipes = new ArrayList<>();
		
		List<Ingredient> requestedIngredients = ingredientRepo.findByIdIn(ingredientIds);
		List<UserRecipe> userRecipes = userRecipeRepo.findByUserId(userId);
	
		for(UserRecipe r : userRecipes) {
			boolean addToList = true;
			List<Ingredient> recipeIngredients = new ArrayList<>();
			for(RecipeUnitIngredient rui : r.getRecipe().getRecipeUnitIngredients()){
				recipeIngredients.add(rui.getUnitIngredient().getIngredient());
			}
			for(Ingredient i : recipeIngredients) {
				if(requestedIngredients.contains(i)) continue;
				else addToList = false;
			}
			if(addToList) {
				SimpleRecipeDTO recipe = new SimpleRecipeDTO();
				recipe.setId(r.getRecipe().getId());
				recipe.setName(r.getRecipe().getName());
				if(!recipes.contains(recipe))
					recipes.add(recipe);
			}
		}
		return recipes;
	}
}
