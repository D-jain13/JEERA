package com.dhairya.JEERA.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.Team;
import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.entity.UserDTO;
import com.dhairya.JEERA.repository.TeamRepo;
import com.dhairya.JEERA.repository.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	TeamRepo teamRepo;

	public ResponseEntity<String> createNewUser(@Valid UserDTO user) {
		try {
			User newUser = new User(user.getUsername(), user.getPassword(), user.getFullName());
			Optional<Team> team = teamRepo.findById(user.getTeamId());
			newUser.setTeam(team.get());
			userRepo.save(newUser);
			return new ResponseEntity<>("Success", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
