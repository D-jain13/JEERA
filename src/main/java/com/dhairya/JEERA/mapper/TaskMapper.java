package com.dhairya.JEERA.mapper;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhairya.JEERA.entity.Project;
import com.dhairya.JEERA.entity.Task;
import com.dhairya.JEERA.entity.TaskRequest;
import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.repository.ProjectRepo;
import com.dhairya.JEERA.repository.UserRepo;

@Component
public class TaskMapper {
	
	@Autowired
    private UserRepo userRepository;

    @Autowired
    private ProjectRepo projectRepository;

    public Task toEntity(TaskRequest taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setCategory(taskDTO.getCategory());
        task.setDue_date(taskDTO.getDue_date());
        task.setLast_updated(LocalDate.now());

        Optional<User> reportee = userRepository.findByUsername(taskDTO.getReportee());
                
        task.setReportee(reportee.get());

        Optional<Project> project = projectRepository.findById(taskDTO.getProject_id());
                
        task.setProject(project.get());

        return task;
    }
    public Set<Task> toEntitySet(Set<TaskRequest> taskDTOs) {
        return taskDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
	
}
