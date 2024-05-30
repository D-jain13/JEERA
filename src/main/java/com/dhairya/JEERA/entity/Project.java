package com.dhairya.JEERA.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JsonManagedReference
	private User lead;
	
	@OneToMany(mappedBy = "project")
	@JsonManagedReference
	private Set<Task> tasks = new HashSet<>();
	
	@ManyToMany
	@JsonManagedReference
	private Set<User> project_members = new HashSet<>();
	
	private LocalDate created;
	
	private LocalDate last_updated;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getLead() {
		return lead;
	}

	public void setLead(User lead) {
		this.lead = lead;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<User> getProject_members() {
		return project_members;
	}

	public void setProject_members(Set<User> project_members) {
		this.project_members = project_members;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(LocalDate last_updated) {
		this.last_updated = last_updated;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String name, String description, User lead, Set<Task> tasks, Set<User> project_members,
			LocalDate created, LocalDate last_updated) {
		super();
		this.name = name;
		this.description = description;
		this.lead = lead;
		this.tasks = tasks;
		this.project_members = project_members;
		this.created = created;
		this.last_updated = last_updated;
	}
	
	
 	
}
