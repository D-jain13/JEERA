package com.dhairya.JEERA.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.entity.ProjectDTO;
import com.dhairya.JEERA.entity.UsernameDTO;
import com.dhairya.JEERA.repository.ProjectRepo;
import com.dhairya.JEERA.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectRepo projectRepo;
	@GetMapping("/getAll")
	private ResponseEntity<Page<Project>> getAllProjects(@PageableDefault(size = 10,sort = "created",direction = Direction.DESC) Pageable pageable){
		return projectService.getAllProjects(pageable);
	}
	
	@GetMapping("get/{id}")
	private ResponseEntity<Project> getProjectById(@PathVariable String id) {
		return projectService.getProjectById(id);
	}
	
	@PostMapping("/create")
	private ResponseEntity<String> createProject(@RequestBody ProjectDTO project){
		return projectService.createProject(project);
	}
	
	@PutMapping("/update/{id}")
	private ResponseEntity<String> updateProjectById(@PathVariable String id ,@RequestBody ProjectDTO project){
		return projectService.updateProjectById(id,project);
	}
	
	@PostMapping("/delete/{id}")
	private ResponseEntity<String> deleteProjectById(@PathVariable String id) {
		return projectService.deleteProjectById(id);
	}
	
	@PatchMapping("/addMembers/{project_id}")
	private ResponseEntity<String> addMembersToProject(@PathVariable String project_id, @RequestBody UsernameDTO usernames){
		return projectService.addMembersToProject(project_id,usernames.getUsernames());
	}
	
	@PatchMapping("/deleteMembers/{project_id}")
	private ResponseEntity<String> deleteMembersToProject(@PathVariable String project_id, @RequestBody UsernameDTO usernames){
		return projectService.deleteMembersToProject(project_id,usernames.getUsernames());
	}
}
