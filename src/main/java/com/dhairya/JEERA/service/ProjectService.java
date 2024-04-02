package com.dhairya.JEERA.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.repository.ProjectRepo;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Service
public class ProjectService {

	@Autowired
	ProjectRepo projectRepo;

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

	public ResponseEntity<String> createProject(Project project) {
		try {

			Project newProject = new Project(project.getName(), project.getDescription(), project.getLead(),
					project.getTasks(), project.getProject_members(), project.getCreated(), project.getLast_updated());
			projectRepo.save(newProject);
			return new ResponseEntity<>(newProject.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateProjectById(Project project) {
		try {
			Optional<Project> existingProject = projectRepo.findById(project.getId());
			if (existingProject.isPresent()) {
				existingProject.get().setName(project.getName());
				existingProject.get().setDescription(project.getDescription());
				existingProject.get().setLead(project.getLead());
				existingProject.get().setTasks(project.getTasks());
				existingProject.get().setProject_members(project.getProject_members());
				existingProject.get().setCreated(project.getCreated());
				existingProject.get().setLast_updated(LocalDate.now());
				projectRepo.save(existingProject.get());

				return new ResponseEntity<>(existingProject.toString(), HttpStatus.ACCEPTED);

			} else {
				return new ResponseEntity<>("Could not find project by id :" + project.getId(), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
