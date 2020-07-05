package com.jerk.chicken.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	public List<UserRole> findByUserId(int id);

}
