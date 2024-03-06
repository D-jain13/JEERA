package com.dhairya.JEERA.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	PasswordEncoder passwordEncoder;

	public ResponseEntity<String> createNewUser(@Valid UserDTO userDTO) {
		try {
			User newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFullName());
			Optional<Team> team = teamRepo.findByName(userDTO.getTeamName());
			newUser.setTeam(team.get());
			String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
			newUser.setPassword(encodedPassword);
			userRepo.save(newUser);
			return new ResponseEntity<>("Success", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> checkCredentials(@Valid UserDTO userDTO) {
		// TODO Auto-generated method stub
		try {
			Optional<User> user = userRepo.findByUsername(userDTO.getUsername());
			if (user.isPresent()) {
				boolean isPasswordCorrect = passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword());
				if (isPasswordCorrect) {
					return new ResponseEntity<String>("Login Successfull", HttpStatus.ACCEPTED);
				} else {
					return new ResponseEntity<String>("Wrong Password!!", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>("User does not exists", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
