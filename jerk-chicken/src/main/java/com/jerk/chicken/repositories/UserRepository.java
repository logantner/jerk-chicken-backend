package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jerk.chicken.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findOneByUserName(String userName);
}
