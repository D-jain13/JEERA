package com.dhairya.JEERA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.dhairya.JEERA.entity.Task;
import com.dhairya.JEERA.service.TaskService;

@RestController
@RequestMapping("/users/")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@GetMapping("getAllTasks")
	private ResponseEntity<List<Task>> getAllTasks(){
		return taskService.getAllTasks();
	}
	 
	@GetMapping("get/{id}")
	private ResponseEntity<Task> getTaskById(@PathVariable String id){
		return taskService.getTaskById(id);
	}
	
	@PostMapping("/create")
	private ResponseEntity<String> createTask(@RequestBody Task task){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return taskService.createTask(task,username);
	}
	
	@PutMapping("/update")
	private ResponseEntity<String> updateTaskById(@RequestBody Task task){
		return taskService.updateTaskById(task);
	}
	
//	@PutMapping("/changeStatus/{id}")
//	private ResponseEntity<String> changeStatus(@)
}
