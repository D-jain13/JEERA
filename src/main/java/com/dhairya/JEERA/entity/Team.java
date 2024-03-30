package com.dhairya.JEERA.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Team {

	@Id
	private String id;
	
	private String name;
	
	@OneToMany(mappedBy = "team")
	private Set<User> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Team(String id, String name, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	
	
}
