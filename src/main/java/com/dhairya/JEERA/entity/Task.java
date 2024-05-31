package com.dhairya.JEERA.entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String task_id;
	
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private LocalDate creation_date;
	
	private LocalDate due_date;
	
	private LocalDate last_updated;
	
	public LocalDate getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(LocalDate last_updated) {
		this.last_updated = last_updated;
	}

	@ManyToOne
	@JsonBackReference
	private Project project;
	
	@ManyToOne
	@JsonManagedReference
	private User assignee;
	
	@ManyToOne
	@JsonManagedReference
	private User reportee;
	
	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getReportee() {
		return reportee;
	}

	public void setReportee(User reportee) {
		this.reportee = reportee;
	}

	public LocalDate getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(LocalDate creation_date) {
		this.creation_date = creation_date;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(String title, String description, Status status, Priority priority, Category category, User reportee,
			LocalDate creation_date, LocalDate due_date, LocalDate last_updated,  Project project) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.category = category;
		this.reportee = reportee;
		this.creation_date = creation_date;
		this.due_date = due_date;
		this.project = project;
		this.last_updated = last_updated;
	}
	
}

enum Status{
	TODO,
	IN_PROGRESS,
	DONE
}
enum Priority{
	CRITICAL,
	HIGH,
	MEDIUM,
	LOW
}
enum Category{
	task,
	bug
}