package com.jerk.chicken.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@OneToMany(mappedBy = "role")
//	private Set<UserRole> roles;
	
	private String role;


	public Role() {
		super();
	}


	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

//	
//
//	public Set<UserRole> getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(Set<UserRole> roles) {
//		this.roles = roles;
//	}


	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	
	

}
