package com.jerk.chicken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jerk.chicken.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
	Role findByRole(String role);

}
