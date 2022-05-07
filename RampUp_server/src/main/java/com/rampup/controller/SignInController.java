package com.rampup.controller;

import java.util.Optional;

import org.json.simple.JSONObject;   
import org.json.simple.JSONValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.rampup.model.User;
import com.rampup.repo.UsersRepository;

@RestController
@CrossOrigin(origins = "*")
public class SignInController {
	@Autowired
	private UsersRepository userrepo;
	
	// APi Example: " http://localhost:8080/getUsers "
	@GetMapping(path = "/getUsers", produces = "application/json")
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.userrepo.findAll());
	}
	
	
	// Api Example " http://localhost:8080/login?username=abc@gmail.com&password=123456 "
	@GetMapping("/login")
	public String login(@RequestParam("username") final String username, 
			    @RequestParam("password") final String password) {
		Optional<User> opuser = userrepo.findById(username);
		if (!opuser.isPresent()) {
			return "No User Exists";
		}
		User user = userrepo.findById(username).orElse(null);
		if (user == null) {
			throw new NullPointerException();
		}
		if(user.getUsername().equals(username) &&
		   user.getPassword().equals(password)) {
			return "Successfull";
		}
		return "Invalid Credentials";
	}
	
	//  Api Example " http://localhost:8080/login "
	//  [And in body both username and password fields should be present]
	@PostMapping("/login")
	public String login(@RequestBody final String jsonBody) {
		Object obj = JSONValue.parse(jsonBody);
		JSONObject jsonObject = (JSONObject) obj;
		String username = (String) jsonObject.get("username");
		String password = (String) jsonObject.get("password");
		System.out.println(username + " " + password + " are values");
		Optional<User> opuser = userrepo.findById(username);
		if (!opuser.isPresent()) {
			return "No User Exists";
		}
		User user = userrepo.findById(username).orElse(null);
		if (user == null) {
			throw new NullPointerException();
		}
		if (user.getUsername().equals(username) &&
		    user.getPassword().equals(password)) {
			return "Successfull";
		}
		return "Invalid Credentials";
	}
}
