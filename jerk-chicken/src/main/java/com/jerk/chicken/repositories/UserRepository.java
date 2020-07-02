package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findOneByUserName(String userName);
	public User findOneByUserNameAndPassword(String userName, String password);
}
