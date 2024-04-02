package com.dhairya.JEERA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhairya.JEERA.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, String> {

}
