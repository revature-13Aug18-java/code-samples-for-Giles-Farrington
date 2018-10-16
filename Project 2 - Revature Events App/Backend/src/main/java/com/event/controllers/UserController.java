package com.event.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.beans.User;
import com.event.services.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users/view/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		if(users.size() == 0) {
			users = null;
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/users/view/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByID(@PathVariable int id) {
		return new ResponseEntity<User>(userService.getById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User u) {
		u = userService.addUser(u);
		if(u == null) {
			return new ResponseEntity<User>(u, HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<User>(u, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value="/users/update", method=RequestMethod.POST)
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		u = userService.update(u);
		if(u == null) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<User>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/users/delete", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@RequestBody User u) {
		userService.delete(u);
		User user = null;
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User u) {
		User user = userService.login(u);
		if(user != null) {
			// Login successful, return the user
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	}
	
}