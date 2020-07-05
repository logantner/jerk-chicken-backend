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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService{

	@Autowired
	UserRepository ur;

	@Autowired
	UserRoleRepository userRoleRepo;
	
	@Autowired
	UserRecipeRepository urr;

	public String login(User user) {
		User u = ur.findByUsername(user.getUsername());
		
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
			
		return generateJWT(roles);
	}

	public String parseObject(String s) {
		return s.substring(s.indexOf('=') + 1);
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

	private String generateJWT(List<Role> roles) {
		Instant now = Instant.now();
	
		byte[] secret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRztTm19A4e9CrlqhWVn+JGQs=");
		String jwt = Jwts.builder().claim("roles", roles).claim("username", "username").setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(24, ChronoUnit.HOURS))).signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return jwt;
	}

}
