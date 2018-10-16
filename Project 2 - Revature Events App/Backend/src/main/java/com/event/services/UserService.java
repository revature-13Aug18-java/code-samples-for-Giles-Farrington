package com.event.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.beans.User;
import com.event.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
	public User getById(int id) {
		return userRepo.findOne(id);
	}
	
	public User addUser(User u) {
		return userRepo.save(u);
	}
	
	public User update(User u) {
		if(u.getId() != 0 && !u.getUsername().isEmpty() && !u.getPassword().isEmpty()){
			userRepo.save(u);
			return u;
		}
		else return null;
	}
	
	public void delete(User u) {
		userRepo.delete(u);
	}

	public User login(User u) {
		User user = userRepo.findByUsername(u.getUsername());
		if(user != null && user.getPassword().equals(u.getPassword())) {
			// Login successful, send user object back
			return user;
		} else {
			return null;
		}
	}
}