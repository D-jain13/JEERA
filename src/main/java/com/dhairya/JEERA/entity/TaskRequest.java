package com.dhairya.JEERA.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TaskRequest {
	String title;
	
	String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	String assignee;
	
	String reportee;
	
	String project_id;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	LocalDate due_date;
	
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
	
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getReportee() {
		return reportee;
	}
	public void setReportee(String reportee) {
		this.reportee = reportee;
	}
	public LocalDate getDue_date() {
		return due_date;
	}
	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}
	public TaskRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	public TaskRequest(String title, String description, Status status, Priority priority, Category category,
			String assignee, String reportee, LocalDate due_date, String project_id) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.category = category;
		this.assignee = assignee;
		this.reportee = reportee;
		this.due_date = due_date;
		this.project_id = project_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	

}

