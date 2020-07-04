package com.jerk.chicken.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.repositories.UserRecipeRepository;
import com.jerk.chicken.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	UserRecipeRepository urr;

	public User login(String user_name) {
		User u = new User();
		u.setUserName(parseObject(user_name));
		return ur.findOneByUserName(u.getUserName());
	}
	
	public String parseObject(String s) {
		return s.substring(s.indexOf('=')+1);
	}
	
	public void deleteUser(User u) {
		ur.delete(u);
	}
	
	public User registerUser(User u) {
		return ur.save(u);
	}
	
	public Recipe addRecipeToRecipeBook(Recipe r, User u) {
		return urr.save(new UserRecipe(0, u, r)).getRecipe();
	}
}
