package com.rampup.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

//import java.util.Optional;
//
//import org.json.simple.JSONObject;    
//import org.json.simple.JSONValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rampup.model.AuthRequest;
import com.rampup.model.User;
import com.rampup.repo.UsersRepository;
import com.rampup.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class SignInController{
	@Autowired
	private UsersRepository userrepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtutil;
	
	// APi Example: " http://localhost:8080/getUsers "
	@GetMapping(path="/getUsers", produces = "application/json" )
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.userrepo.findAll());
	}
	
	
//	// Api Example " http://localhost:8080/login?username=abc@gmail.com&password=123456 "
//	@GetMapping("/login")
//	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
//		Optional<User> opuser = userrepo.findById(username);
//		if(!opuser.isPresent()) {
//			return "No User Exists";
//		}
//		User user = userrepo.findById(username).orElse(null);
//		if(user == null) {
//			throw new NullPointerException();
//		}
//		if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
//			return "Successfull";
//		}
//		return "Invalid Credentials";
//	}
//	
//	//  Api Example " http://localhost:8080/login " [And in body both username and password fields should be present]
//	@PostMapping("/login")
//	public String login(@RequestBody String jsonBody) {
//		Object obj = JSONValue.parse(jsonBody);
//		JSONObject jsonObject = (JSONObject) obj;
//		String username = (String)jsonObject.get("username"); 
//		String password = (String)jsonObject.get("password");
//		System.out.println(username + " " + password + " are values");
//		Optional<User> opuser = userrepo.findById(username);
//		if(!opuser.isPresent()) {
//			return "No User Exists";
//		}
//		User user = userrepo.findById(username).orElse(null);
//		if(user == null) {
//			throw new NullPointerException();
//		}
//		if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
//			return "Successfull";
//		}
//		return "Invalid Credentials";
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest){
		Boolean validCredentials = true;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
		} catch(Exception ex) {
			validCredentials = false;
		}
		if (validCredentials){
			String token = jwtutil.generateToken(authRequest.getUsername());
			ResponseCookie jwttokenCookie = ResponseCookie.from("token", token)
					.httpOnly(true)
				    .secure(true)
				    .path("/")
				    .maxAge(60)
				    .build();
			
			
			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwttokenCookie.toString()).build();
		}else {
			return new ResponseEntity<>("Invalid Username/Password",HttpStatus.UNAUTHORIZED);
		}
		
	}
}
