package com.jerk.chicken.models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User u;
	
	@Before
	public void setUp() {
		u = new User(1,"user","pass");
	}
	
	@Test
	public void testGetId() {
		assertEquals(u.getId(),1);
	}
	
	@Test
	public void testSetId() {
		u.setId(2);
		assertEquals(u.getId(),2);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals(u.getUsername(), "user");
	}
	
	@Test
	public void testSetUsername() {
		u.setUsername("newUser");
		assertEquals(u.getUsername(), "newUser");
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(u.getPassword(),"pass");
	}
	
	@Test
	public void testSetPassword() {
		u.setPassword("newPass");
		assertEquals(u.getPassword(),"newPass");
	}
	
	@Test
	public void testSetUserRoles() {
		Set<UserRole> roles = new HashSet<>();
		roles.add(new UserRole(0, new Role(2, "user"), u, true));
		u.setUserRoles(roles);
		assertNotNull(u.getUserRoles());
	}
}
