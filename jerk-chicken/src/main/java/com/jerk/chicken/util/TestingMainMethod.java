package com.jerk.chicken.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestingMainMethod {

	public static void main(String[] args) {
	
		
		
//		
//		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
//		
//		String a = b.encode("password");
//		
//		System.out.println(a);
//		
//		System.out.println(b.matches("password", "$2a$10$K2OpyJQTvDvbcEtsoKplge3Q5g0C659SJilySjXlQZGw3ZnHCaumO"));
		
//		Instant now = Instant.now();
//		
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
//				.setSigningKey(testSecret)
//				.parseClaimsJws(jwt);
//		
//		System.out.println(result);

	}

}
