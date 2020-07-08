package com.jerk.chicken.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jerk.chicken.models.Role;
import com.jerk.chicken.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtValidate {
	
	private byte[] secret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRztTm19A4e9CrlqhWVn+JGQs=");
	
	public Object validateJwt(String jwt, String returnData) {
		Jws<Claims> result = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(jwt);
		return result.getBody().get(returnData);
	}
	
	public String generateJWT(List<Role> roles, User user) {
		Instant now = Instant.now();
		String jwt = Jwts.builder().claim("roles", roles).claim("username", user.getUsername()).claim("id", user.getId())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(24, ChronoUnit.HOURS))).signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		return jwt;
	}
	
	public List<Role> getRoles(Object o){
		ArrayList list = (ArrayList) o;
		ArrayList<Role> roles = new ArrayList<>();
		for(Object r : list) {
			LinkedHashMap map = (LinkedHashMap) r;
			roles.add(new Role((int) map.get("id"), (String) map.get("role")));
		}
		return roles;
	}
	public class UserData{
		int id;
		String username;
		List<Role> roles;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public List<Role> getRoles() {
			return roles;
		}
		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
	}
	public UserData getUserData(String token){
		UserData u = new UserData();
		u.setId((int)validateJwt(token, "id"));
		u.setUsername((String)validateJwt(token,"username"));
		u.setRoles(getRoles(validateJwt(token,"roles")));
		return u;
	}
}
