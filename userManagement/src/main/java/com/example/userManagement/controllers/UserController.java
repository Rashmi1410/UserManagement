package com.example.userManagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.userManagement.entities.User;
import com.example.userManagement.exceptions.UserExistsException;
import com.example.userManagement.exceptions.UserNameNotFoundException;
import com.example.userManagement.exceptions.UserNotFoundException;
import com.example.userManagement.services.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUser();
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user , UriComponentsBuilder builder)
	{
		try {
			return userService.createUser(user);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
		
	}
	
	@PutMapping("/user/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user)
	{
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
	   userService.deleteUserById(id);
	}
	
	@GetMapping("/user/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException
	{
		User user =  userService.getUserByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username: "+username+" not found in user repository");
		return user;
	}

}
