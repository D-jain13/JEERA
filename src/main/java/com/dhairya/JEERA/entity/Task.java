package com.dhairya.JEERA.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	
	private String assignee;
	
	private String reportee;
	
	private Date creation_date;
	
	private Date due_date;

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

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
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

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Task(String title, String description, Status status, Priority priority, String assignee, String reportee,
			Date creation_date, Date due_date) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.assignee = assignee;
		this.reportee = reportee;
		this.creation_date = creation_date;
		this.due_date = due_date;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

enum Status{
	TODO,
	IN_PROGRESS,
	DONE
}
enum Priority{
	HIGH,
	MEDIUM,
	LOW
}