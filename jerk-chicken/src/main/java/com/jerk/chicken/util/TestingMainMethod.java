package com.jerk.chicken.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.jerk.chicken.models.Role;
import com.jerk.chicken.models.User;

public class TestingMainMethod {

//	@Autowired
//	static JwtValidate jwt;
	
	public static void main(String[] args) {
	
		
		
//		
//		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
//		
//		String a = b.encode("password");
//		
//		System.out.println(a);
//		
//		System.out.println(b.matches("password", "$2a$10$K2OpyJQTvDvbcEtsoKplge3Q5g0C659SJilySjXlQZGw3ZnHCaumO"));
		
		Instant now = Instant.now();
		
//		byte[] secret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRztTm19A4e9CrlqhWVn+JGQs=");
//		byte[] testSecret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRasdfasdf19A4e9CrlqhWVn+JGQs=");
//		
//		String jwt = Jwts.builder()
//				.claim("role", "user")
//				.claim("username", "username")
//				.setIssuedAt(Date.from(now))
//				.setExpiration(Date.from(now.plus(24,ChronoUnit.HOURS)))
//				.signWith(SignatureAlgorithm.HS256, secret)
//				.compact();
//		
//		
//		System.out.println(jwt);
//		
//		
//		Jws<Claims> result = Jwts.parser()
//				.require("role","user")
//				.setSigningKey(secret)
//				.parseClaimsJws(jwt);
//		
//		System.out.println(result.getBody().get("role"));
		JwtValidate jwt = new JwtValidate();
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(1, "user"));
		roles.add(new Role(2, "admin"));
		User u = new User(1, "bob", "bobspassword");
		String token = jwt.generateJWT(roles, u);
		ArrayList o = (ArrayList) jwt.validateJwt(token, "roles");
		for(Object r : o) {
			LinkedHashMap map = (LinkedHashMap) r;
			System.out.println(map.get("role"));
		}
	}
}
