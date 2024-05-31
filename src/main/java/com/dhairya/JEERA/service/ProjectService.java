package com.dhairya.JEERA.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import jakarta.transaction.Transactional;

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

	public ResponseEntity<Page<Project>> getAllProjects(Pageable pageable) {
		Page<Project> projects = projectRepo.findAll(pageable);
		return new ResponseEntity<>(projects, HttpStatus.OK);
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

			if (project_lead.isEmpty()) {
				return new ResponseEntity<>("Project lead cannot be found", HttpStatus.NOT_FOUND);
			} else {
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
			return new ResponseEntity<String>("Project is created successfully with id - " + newProject.getId(),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateProjectById(String id, ProjectDTO project) {
		try {
			Optional<Project> existingProject = projectRepo.findById(id);

			if (existingProject.isEmpty()) {
				return new ResponseEntity<>("Could not find project by id :" + id, HttpStatus.NOT_FOUND);
			}

			existingProject.get().setName(project.getName());
			existingProject.get().setDescription(project.getDescription());

			Optional<User> project_lead = userRepo.findByUsername(project.getLead_username());

			if (project_lead.isEmpty()) {
				return new ResponseEntity<>("Project lead cannot be found", HttpStatus.NOT_FOUND);
			}

			existingProject.get().setLead(project_lead.get());

			Set<Task> tasks = taskMapper.toEntitySet(project.getTasks());
			existingProject.get().setTasks(tasks);

			Set<User> projectMembers = userMapper.toEntitySet(project.getProject_member_username());

			existingProject.get().setProject_members(projectMembers);

			// existingProject.get().setCreated(project.getCreated());
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

			if (project.isEmpty()) {
				return new ResponseEntity<String>("Could not find project by id", HttpStatus.NOT_FOUND);
			}

			projectRepo.deleteById(id);
			return new ResponseEntity<String>("Project with id - " + id + "is deleted successfully",
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@Transactional
	public ResponseEntity<String> addMembersToProject(String project_id, Set<String> usernames) {
		
		try {
			Optional<Project> project = projectRepo.findById(project_id);
			
			if(project.isEmpty()) {
				return new ResponseEntity<String>("Could not find project with the given id",HttpStatus.NOT_FOUND);
			}
			
			Set<User> user = new HashSet<>();
	        for (String username : usernames) {
	            Optional<User> userOptional = userRepo.findByUsername(username);
	            if (userOptional.isPresent()) {
	            	user.add(userOptional.get());
	            } else {
	                return new ResponseEntity<>("User with username " + username + " not found", HttpStatus.BAD_REQUEST);
	            }
	        }
				project.get().getProject_members().addAll(user);
				projectRepo.save(project.get());
				
				Set<String> memberUsernames = project.get().getProject_members().stream()
			            .map(User::getUsername)  
			            .collect(Collectors.toSet());
				
				return new ResponseEntity<String>("User Added Successfully" + '\n' + "Project with id - " + project_id + "contains following members" + memberUsernames,HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@Transactional
	public ResponseEntity<String> deleteMembersToProject(String project_id, Set<String> usernames) {
		
		try {
			Optional<Project> project = projectRepo.findById(project_id);
			
			if(project.isEmpty()) {
				return new ResponseEntity<String>("Could not find project with the given id",HttpStatus.NOT_FOUND);
			}
			
			Set<User> user = new HashSet<>();
	        for (String username : usernames) {
	            Optional<User> userOptional = userRepo.findByUsername(username);
	            if (userOptional.isPresent()) {
	            	user.add(userOptional.get());
	            } else {
	                return new ResponseEntity<>("User with username " + username + " not found", HttpStatus.BAD_REQUEST);
	            }
	        }
				project.get().getProject_members().removeAll(user);
				projectRepo.save(project.get());
				
				Set<String> memberUsernames = project.get().getProject_members().stream()
			            .map(User::getUsername)  
			            .collect(Collectors.toSet());
			            
				return new ResponseEntity<String>("User Removed successfully "+ '\n'+ "Project with id - " + project_id + " contains following members" + memberUsernames,HttpStatus.ACCEPTED);
				
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

}
