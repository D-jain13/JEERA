package com.dhairya.JEERA.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.entity.ProjectDTO;
import com.dhairya.JEERA.entity.Task;
import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.mapper.TaskMapper;
import com.dhairya.JEERA.mapper.UserMapper;
import com.dhairya.JEERA.repository.ProjectRepo;
import com.dhairya.JEERA.repository.UserRepo;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Service
public class ProjectService {

	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	TaskMapper taskMapper;
	
	@Autowired
	UserMapper userMapper;

	public ResponseEntity<List<Project>> getAllProjects() {

		List<Project> projectList = projectRepo.findAll();
		return new ResponseEntity<>(projectList, HttpStatus.OK);

	}

	public ResponseEntity<Project> getProjectById(String id) {
		try {
			Optional<Project> projectOptional = projectRepo.findById(id);
			if (projectOptional.isPresent()) {
				Project project = projectOptional.get();
				return new ResponseEntity<>(project, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> createProject(ProjectDTO project) {
		try {
			Optional<User> project_lead = userRepo.findByUsername(project.getLead_username());
			
			if(project_lead.isEmpty()) {
				return new ResponseEntity<>("Project lead cannot be found",HttpStatus.NOT_FOUND);
			}
			else {
				System.out.println(project_lead.get().getFullName());
			}
			
			Project newProject = new Project();
			newProject.setName(project.getName());
			newProject.setDescription(project.getDescription());
			newProject.setLead(project_lead.get());
			
			Set<Task> tasks = taskMapper.toEntitySet(project.getTasks());
			newProject.setTasks(tasks);
			newProject.setCreated(LocalDate.now());
			newProject.setLast_updated(LocalDate.now());
			
			Set<User> projectMembers = userMapper.toEntitySet(project.getProject_member_username());
	        newProject.setProject_members(projectMembers);
	        
	        projectRepo.save(newProject);
	        return new ResponseEntity<String>("Project is created successfully with id - " + newProject.getId(),HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateProjectById(String id, ProjectDTO project) {
		try {
			Optional<Project> existingProject = projectRepo.findById(id);
			
			if(existingProject.isEmpty()) {
				return new ResponseEntity<>("Could not find project by id :" + id, HttpStatus.NOT_FOUND);
			}
		
				existingProject.get().setName(project.getName());
				existingProject.get().setDescription(project.getDescription());
				
				Optional<User> project_lead = userRepo.findByUsername(project.getLead_username());
				
				if(project_lead.isEmpty()) {
					return new ResponseEntity<>("Project lead cannot be found",HttpStatus.NOT_FOUND);
				}
				
				existingProject.get().setLead(project_lead.get());
				
				Set<Task> tasks = taskMapper.toEntitySet(project.getTasks());
				existingProject.get().setTasks(tasks);
				
				Set<User> projectMembers = userMapper.toEntitySet(project.getProject_member_username());
		        
				existingProject.get().setProject_members(projectMembers);
				
				//existingProject.get().setCreated(project.getCreated());
				existingProject.get().setLast_updated(LocalDate.now());
				projectRepo.save(existingProject.get());

				return new ResponseEntity<>("Successfully Updated project", HttpStatus.ACCEPTED);

			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> deleteProjectById(String id) {
		try {
			Optional<Project> project = projectRepo.findById(id);
			
			if(project.isEmpty()) {
				return new ResponseEntity<String>("Could not find project by id",HttpStatus.NOT_FOUND);
			}
			
			projectRepo.deleteById(id);
			return new ResponseEntity<String>("Project with id - " + id + "is deleted successfully",HttpStatus.ACCEPTED);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}

	
}
