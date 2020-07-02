package com.jerk.chicken.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerk.chicken.models.User;
import com.jerk.chicken.repositories.UserRepository;

@Controller
public class UserService {
	@Autowired
	UserRepository ur;
	
	@PostMapping("/login")
	@ResponseBody
	public User login(@RequestBody String user_name) {
		User u = new User();
		u.setUserName(user_name.substring(user_name.indexOf('=')+1));
		return ur.findOneByUserName(u.getUserName());
	}
}
