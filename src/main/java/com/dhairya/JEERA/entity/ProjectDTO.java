package com.dhairya.JEERA.entity;

import java.time.LocalDate;
import java.util.Set;

public class ProjectDTO {

	private String name;

	private String description;

	private String lead_username;

	private Set<TaskRequest> tasks;

	private Set<String> project_member_username;

	private LocalDate last_updated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLead_username() {
		return lead_username;
	}

	public void setLead_username(String lead_username) {
		this.lead_username = lead_username;
	}

	public Set<TaskRequest> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskRequest> tasks) {
		this.tasks = tasks;
	}

	public Set<String> getProject_member_username() {
		return project_member_username;
	}

	public void setProject_member_username(Set<String> project_member_username) {
		this.project_member_username = project_member_username;
	}


	public LocalDate getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(LocalDate last_updated) {
		this.last_updated = last_updated;
	}

	public ProjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectDTO(String name, String description, String lead_username, Set<TaskRequest> tasks,
			Set<String> project_member_username) {
		super();
		this.name = name;
		this.description = description;
		this.lead_username = lead_username;
		this.tasks = tasks;
		this.project_member_username = project_member_username;
		this.last_updated = LocalDate.now();
	}

}
