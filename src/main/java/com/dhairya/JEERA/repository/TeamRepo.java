package com.dhairya.JEERA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhairya.JEERA.entity.Team;
import java.util.List;
import java.util.Optional;


@Repository
public interface TeamRepo extends JpaRepository<Team, String>{

	Optional<Team> findByName(String name);
}
