package com.dhairya.JEERA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhairya.JEERA.entity.UserDTO;
import com.dhairya.JEERA.service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	private String test() {
		return "test";
	}
	
	@PostMapping("/register")
	private ResponseEntity<String> createNewUser(@Valid @RequestBody UserDTO user) {
		return userService.createNewUser(user);
	}
	
	@GetMapping("/login")
	private ResponseEntity<String> checkCredentials(@Valid @RequestBody UserDTO user) {
		return userService.checkCredentials(user);
	}
	
	@PostMapping("/login")
	private String loginSucessRedirectToHome() {
		return "redirect:/dashboard";
	}
	
	
}
