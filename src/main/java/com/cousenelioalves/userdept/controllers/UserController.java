package com.cousenelioalves.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cousenelioalves.userdept.entities.User;
import com.cousenelioalves.userdept.repositories.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserRepository repository;	
	
	@GetMapping(value = "/list")
	public List<User> getUserList() {
		List<User> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public User	getUser(@PathVariable Long id) {
		User result = repository.findById(id).get();
		return result;
	}
	
	@PostMapping
	public User postUser(@RequestBody User user) {
		User result = repository.save(user);
		return result;
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
		return ("User deleted successfully");
	}
	
	@PatchMapping(value = "/patch/{id}")
	public User patchUser(@PathVariable Long id, @RequestBody User user) {
		User result = repository.findById(id).get();
		result.setName(user.getName());
		result.setEmail(user.getEmail());
		return repository.save(result);
	}
}
