package com.rampup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rampup.model.User;
import com.rampup.repo.UserCredentialsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	@Autowired
	private UserCredentialsRepository userrepo;
	
//	@PostMapping("/addUser")
//	public ResponseEntity<?> addUser(Users user){
//		System.out.println("User = " + user);
//		Users saveduser = this.userrepo.save(user);
//		return ResponseEntity.ok(saveduser);
//	}
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user){
		System.out.println("User = " + user);
		User saveduser = this.userrepo.save(user);
		return ResponseEntity.ok(saveduser);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.userrepo.findAll());
	}
}
