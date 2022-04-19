package com.practice.example.practice;

import com.practice.example.practice.models.User;
import com.practice.example.practice.repositories.TaskRepository;
import com.practice.example.practice.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class PracticeApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRepository taskRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void createUser(){
		User user = new User("auth0|625d60ed379bfd006f275c70");
		userRepository.save(user);
		assertEquals(1, userRepository.findAll().size());
	}

	@Test
	public void createUniqueUsers(){
		String userID = "auth0|625d60ed379bfd006f275c70";
		Optional foundUser = userRepository.findByAuthId(userID);
		if ( !foundUser.isPresent() ){
			User newUser = new User(userID);
			userRepository.save(newUser);
		}
		assertEquals(1, userRepository.findAll().size());
	}

	@Test
	public void createNewUsers(){
		String userID = "auth0|625d60ed379bfd006f275c71";
		Optional foundUser = userRepository.findByAuthId(userID);
		if ( !foundUser.isPresent() ){
			User newUser = new User(userID);
			userRepository.save(newUser);
		}
		String userID2 = "auth0|625d60ed379bfd006f275c71";
		Optional foundUser2 = userRepository.findByAuthId(userID2);
		if ( !foundUser2.isPresent() ){
			User newUser2 = new User(userID2);
			userRepository.save(newUser2);
		}
		assertEquals(1, userRepository.findAll().size());
	}

}
