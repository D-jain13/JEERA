package com.dhairya.JEERA.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhairya.JEERA.entity.User;
import com.dhairya.JEERA.repository.UserRepo;

@Component
public class UserMapper {
	  @Autowired
	    private UserRepo userRepository;

	    public User toEntity(String username) {
	        return userRepository.findByUsername(username).get();
	                
	    }

	    public Set<User> toEntitySet(Set<String> usernames) {
	        return usernames.stream()
	                .map(this::toEntity)
	                .collect(Collectors.toSet());
	    }
}
