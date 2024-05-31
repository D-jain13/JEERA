package com.dhairya.JEERA.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.entity.Task;
import com.dhairya.JEERA.entity.TaskRequest;
import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.repository.ProjectRepo;
import com.dhairya.JEERA.repository.TaskRepo;
import com.dhairya.JEERA.repository.UserRepo;

@Service
public class TaskService {

	@Autowired
	TaskRepo taskRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProjectRepo projectRepo;

	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> taskList = taskRepo.findAll();
		return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
	}

	public ResponseEntity<Task> getTaskById(String id) {
		try {
			Optional<Task> taskOptional = taskRepo.findById(id);
			if (taskOptional.isPresent()) {
				Task task = taskOptional.get();
				return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> createTask(TaskRequest task) {
		try {
			Optional<User> assignee = userRepo.findByUsername(task.getAssignee());
			
			if(assignee.isEmpty()) {
				return new ResponseEntity<String>("Cannot find assignee",HttpStatus.NOT_FOUND); 
			}
			
			Optional<User> reportee = userRepo.findByUsername(task.getReportee());
			
			if(reportee.isEmpty()) {
				return new ResponseEntity<String>("Cannot find reportee",HttpStatus.NOT_FOUND); 
			}
			
			Optional<Project> project = projectRepo.findById(task.getProject_id());
			
			if(project.isEmpty()) {
				return new ResponseEntity<String>("Cannot find project",HttpStatus.NOT_FOUND); 
			}
			
			Task newTask = new Task();
			newTask.setTitle(task.getTitle());
			newTask.setDescription(task.getDescription());
			newTask.setStatus(task.getStatus());
			newTask.setPriority(task.getPriority());
			newTask.setCategory(task.getCategory());
			newTask.setCreation_date(LocalDate.now());
			newTask.setDue_date(task.getDue_date());
			newTask.setLast_updated(LocalDate.now());
			newTask.setAssignee(assignee.get());
			newTask.setReportee(reportee.get());
			newTask.setProject(project.get());
			
			taskRepo.save(newTask);
			
			return new ResponseEntity<String>("Task is created succesfully with id - " + newTask.getTask_id(),HttpStatus.ACCEPTED);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateTaskById(String task_id, TaskRequest task) {
		try {

			Optional<Task> existingTask = taskRepo.findById(task_id);
			
			if(existingTask.isEmpty()) {
				return new ResponseEntity<>("Cannot find task with task id - " + task_id,
						HttpStatus.NOT_FOUND);
			}
			
				existingTask.get().setTitle(task.getTitle());
				existingTask.get().setDescription(task.getDescription());
				existingTask.get().setStatus(task.getStatus());
				existingTask.get().setPriority(task.getPriority());
				existingTask.get().setCategory(task.getCategory());
				existingTask.get().setDue_date(task.getDue_date());
				Optional<User> assignee = userRepo.findByUsername(task.getAssignee());
				
				if(assignee.isEmpty()) {
					return new ResponseEntity<String>("Cannot find assignee",HttpStatus.NOT_FOUND); 
				}
				
				existingTask.get().setAssignee(assignee.get());
				
				Optional<User> reportee = userRepo.findByUsername(task.getReportee());
				
				if(reportee.isEmpty()) {
					return new ResponseEntity<String>("Cannot find reportee",HttpStatus.NOT_FOUND); 
				}
				existingTask.get().setReportee(reportee.get());
				
				existingTask.get().setLast_updated(LocalDate.now());

				taskRepo.save(existingTask.get());

				return new ResponseEntity<>("Task is succesfully Updated", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
