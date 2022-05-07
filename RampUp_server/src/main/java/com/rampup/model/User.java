package com.rampup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rampup.enums.UserClass;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	@Id
	private String username;
	private String password;
	private String name;
	private UserClass userClass;	
}
