package com.dhairya.JEERA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhairya.JEERA.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, String> {
	
	
}
