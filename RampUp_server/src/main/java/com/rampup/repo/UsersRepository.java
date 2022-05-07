package com.rampup.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rampup.model.User;

public interface UsersRepository extends MongoRepository<User, String>{

}
