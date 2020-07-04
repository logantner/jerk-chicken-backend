package com.jerk.chicken.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



public class TestingMainMethod {

	public static void main(String[] args) {
		
		Instant now = Instant.now();
		
		byte[] secret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRztTm19A4e9CrlqhWVn+JGQs=");
		byte[] testSecret = Base64.getDecoder().decode("m+xugUWvhJ2d7q6JoObRasdfasdf19A4e9CrlqhWVn+JGQs=");
		
		String jwt = Jwts.builder()
				.claim("role", "user")
				.claim("username", "username")
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(24,ChronoUnit.HOURS)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
		
		System.out.println(jwt);
		
		
		Jws<Claims> result = Jwts.parser()
				.require("role","user")
				.setSigningKey(testSecret)
				.parseClaimsJws(jwt);
		
		System.out.println(result);

	}

}
