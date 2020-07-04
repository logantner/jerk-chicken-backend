package com.jerk.chicken.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.Recipe;
import com.jerk.chicken.models.User;
import com.jerk.chicken.models.UserRecipe;
import com.jerk.chicken.repositories.UserRecipeRepository;
import com.jerk.chicken.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

	@Autowired
	UserRepository ur;

	@Autowired
	UserRecipeRepository urr;

	public String login(String user_name) {
		//User u = new User();
		//u.setUserName(parseObject(user_name));
		User u = ur.findOneByUsername(user_name);
		
		return generateJWT();
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

	private String generateJWT() {
		Instant now = Instant.now();

		byte[] secret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRztTm19A4e9CrlqhWVn+JGQs=");
		String jwt = Jwts.builder().claim("role", "user").claim("username", "username").setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(24, ChronoUnit.HOURS))).signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return jwt;
	}
}
