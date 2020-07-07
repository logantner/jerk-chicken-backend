package com.jerk.chicken.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author James
 * @model
 * <h2>User Model</h2>
 * <h4></h4>
 * <ul>
 * 	<li>id - generated</li>
 * </ul>
 */
@Entity
@Component
@Table(name="users")
public class User implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@OneToMany(mappedBy="role")
	@JsonIgnore
	private Set<UserRole> roles;
	
//	@OneToMany(mappedBy="id", fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SELECT)
//	private List<Integer> ownedRecipes;
//	
//	@Fetch(FetchMode.SELECT)
//	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
//	private List<UserRecipe> favoriteRecipes;

	public User() {
		super();
	
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.roles = new HashSet<UserRole>();
		//this.ownedRecipes = new ArrayList<>();
	
	}

	public User(int id, String userName, String password
			) {
		super();
		this.id = id;
		this.username = userName;
		this.password = password;
		//this.ownedRecipes = ownedRecipes;

	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getUserRoles() {
		return roles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.roles = userRoles;
	}

//	public List<Integer> getOwnedRecipes() {
//		return ownedRecipes;
//	}
//
//	public void setOwnedRecipes(List<Integer> ownedRecipes) {
//		this.ownedRecipes = ownedRecipes;
//	}

//	public List<UserRecipe> getFavoriteRecipes() {
//		return favoriteRecipes;
//	}
//
//	public void setFavoriteRecipes(List<UserRecipe> favoriteRecipes) {
//		this.favoriteRecipes = favoriteRecipes;
//	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", userRoles=" + roles ;
				
	}

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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
}
