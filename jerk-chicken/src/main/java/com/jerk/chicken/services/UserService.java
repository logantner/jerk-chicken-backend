package com.jerk.chicken.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerk.chicken.models.User;
import com.jerk.chicken.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;

	public User login(String user_name) {
		User u = new User();
		u.setUserName(user_name.substring(user_name.indexOf('=')+1));
		return ur.findOneByUserName(u.getUserName());
	}
}
