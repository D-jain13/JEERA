package com.dhairya.JEERA.entity;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class UserDTO {
	
	@Size(min = 5, message = "Username should be of 5 length")
	private String username;
	
	@Size(min = 6, message = "Password should be of 6 length")
	private String password;
	
	private String fullName;
	
	private String teamId;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String username, String password, String fullName, String teamId) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.teamId = teamId;
	}

}
