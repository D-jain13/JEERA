package com.dhairya.JEERA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhairya.JEERA.entity.UserDTO;
import com.dhairya.JEERA.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService  ;
	
	@PostMapping("/create")
	private ResponseEntity<String> createNewUser(@Valid @RequestBody UserDTO user) {
		return userService.createNewUser(user);
	}
}
