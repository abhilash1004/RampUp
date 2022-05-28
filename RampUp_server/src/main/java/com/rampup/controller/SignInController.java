package com.rampup.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rampup.model.AuthRequest;
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
