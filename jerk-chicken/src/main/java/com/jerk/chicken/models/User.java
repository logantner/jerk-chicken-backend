package com.jerk.chicken.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name="jerk_user")
public class User implements Serializable{


	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String userName;
	
	private String password;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	private List<UserRole> userRoles;
	
	
//	@OneToMany(mappedBy="id", fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SELECT)
//	private List<Integer> ownedRecipes;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
	private List<UserRecipe> favoriteRecipes;

	public User() {
		super();
	}
	
	public User(String username, String password) {
		this.userName = username;
		this.password = password;
		this.userRoles = new ArrayList<>();
		//this.ownedRecipes = new ArrayList<>();
		this.favoriteRecipes = new ArrayList<>();
	}

	public User(int id, String userName, String password, List<UserRole> userRoles, 
			List<UserRecipe> favoriteRecipes) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userRoles = userRoles;
		//this.ownedRecipes = ownedRecipes;
		this.favoriteRecipes = favoriteRecipes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

//	public List<Integer> getOwnedRecipes() {
//		return ownedRecipes;
//	}
//
//	public void setOwnedRecipes(List<Integer> ownedRecipes) {
//		this.ownedRecipes = ownedRecipes;
//	}

	public List<UserRecipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<UserRecipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userRoles=" + userRoles 
				+ ", favoriteRecipes=" + favoriteRecipes + "]";
	}
	
	
	
	
	
	
	
	
}
