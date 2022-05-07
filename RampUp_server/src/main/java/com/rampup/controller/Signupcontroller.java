package com.rampup.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rampup.enums.UserClass;
import com.rampup.model.User;
import com.rampup.repo.UsersRepository;

@RestController
@CrossOrigin(origins = "*")
public class Signupcontroller {
	@Autowired
	UsersRepository usersdb;
	
	// Api Example http://localhost:8080/addConsumer {In body user class fields should be present}
	@PostMapping("/addConsumer")
	public String signupConsumer(@RequestBody User user) {
		if(user.getUsername() != null) {
			Optional<User> duplicateUser = usersdb.findById(user.getUsername());
			if(!checkUserNameinEmailFormat(user.getUsername())) {
				return "Please Enter Valid Credentials";
			}
			if(duplicateUser.isPresent()) {
				return "User already Existing";
			}	
			user.setUserClass(UserClass.Consumer);
			User saveduser = this.usersdb.save(user);
			return "User added: " + saveduser.toString();
		}
		return "Please Enter Valid Credentials";
	}
	
	//  Api Example http://localhost:8080/deleteConsumer/abhi1004
	@DeleteMapping("/deleteConsumer/{username}")
	public String deleteConsumer(@PathVariable String username) {
		try {
			usersdb.deleteById(username);
		}
		catch(IllegalArgumentException ex) {
			return "No username provided";
		}
		return "User Deleted"; 
	}
	
	private Boolean checkUserNameinEmailFormat(String username) {
		String regexCheck = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regexCheck);
		if(username == null) {
			return false;
		}
		return pattern.matcher(username).matches();
	}
	
	
}
