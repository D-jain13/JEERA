package com.dhairya.JEERA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@GetMapping("/getAll")
	private ResponseEntity<List<Project>> getAllProjects(){
		return projectService.getAllProjects();
	}
	
	@GetMapping("get/{id}")
	private ResponseEntity<Project> getProjectById(@PathVariable String id) {
		return projectService.getProjectById(id);
	}
	
	@PostMapping("/create")
	private ResponseEntity<String> createProject(@RequestBody Project project){
		return projectService.createProject(project);
	}
	
	@PutMapping("/update/{id}")
	private ResponseEntity<String> updateProjectById(@RequestBody Project project){
		return projectService.updateProjectById(project);
	}
}
