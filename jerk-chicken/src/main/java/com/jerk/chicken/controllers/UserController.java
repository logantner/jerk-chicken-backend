package com.jerk.chicken.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerk.chicken.models.User;
import com.jerk.chicken.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService us;
	
	@PostMapping("/login")
	@ResponseBody
	public User login(@RequestBody User user) {
		
		System.out.println(user.getUserName());
		return us.login(user.getUserName());
	}
}
