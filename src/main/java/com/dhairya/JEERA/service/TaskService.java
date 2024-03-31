package com.dhairya.JEERA.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.Task;
import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.repository.TaskRepo;
import com.dhairya.JEERA.repository.UserRepo;

@Service
public class TaskService {

	@Autowired
	TaskRepo taskRepo;

	@Autowired
	UserRepo userRepo;

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

	public ResponseEntity<String> createTask(Task task, String username) {
		try {
			Optional<User> assignee = userRepo.findByUsername(username);

			if (assignee.isEmpty()) {
				return new ResponseEntity<String>("Assignee with username - " + username + " not found",
						HttpStatus.NOT_FOUND);
			}

			if (task.getCreation_date() == null) {
				task.setCreation_date(new Date());
			}

			task.setAssignee(assignee.get());

			Task savedTask = taskRepo.save(task);

			if (savedTask != null) {
				return new ResponseEntity<String>("Task created successfully with id - " + savedTask.getTask_id(),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Failed to create task", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateTaskById(Task task) {
		try {

			Optional<Task> existingTask = taskRepo.findById(task.getTask_id());
			if (existingTask.isPresent()) {
				existingTask.get().setTitle(task.getTitle());
				existingTask.get().setDescription(task.getDescription());
				existingTask.get().setStatus(task.getStatus());
				existingTask.get().setPriority(task.getPriority());
				existingTask.get().setCategory(task.getCategory());
				existingTask.get().setCreation_date(task.getCreation_date());
				existingTask.get().setDue_date(task.getCreation_date());
				existingTask.get().setAssignee(task.getAssignee());
				existingTask.get().setReportee(task.getReportee());
				existingTask.get().setLast_updated(LocalDate.now());

				taskRepo.save(existingTask.get());

				return new ResponseEntity<>(existingTask.get().toString(), HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Cannot find task with task id - " + task.getTask_id(),
						HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
