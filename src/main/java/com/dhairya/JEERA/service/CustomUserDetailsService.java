package com.dhairya.JEERA.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.repository.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByUsername(username);
		return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
	}

}
