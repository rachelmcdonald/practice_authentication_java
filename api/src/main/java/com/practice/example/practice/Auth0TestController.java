package com.practice.example.practice;

import com.practice.example.practice.models.Task;
import com.practice.example.practice.models.User;
import com.practice.example.practice.repositories.TaskRepository;
import com.practice.example.practice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth0")
public class Auth0TestController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/public")
    public ResponseEntity<ResponseDTO> publicEndpoint() {
        return ResponseEntity.ok(new ResponseDTO("Public Endpoint Working fine!"));
    }

    @GetMapping(value = "/private")
    public ResponseEntity<ResponseDTO> privateEndPoint() {
        return ResponseEntity.ok(new ResponseDTO("Private Endpoint Working fine !"));
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Task>> getAllUserTasks() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(taskRepository.findByUserAuthId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity postUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//      try to find user
        Optional foundUser = userRepository.findByAuthId(userId);
        //  if there is no user that matches then save the user, otherwise do nothing
        if ( !foundUser.isPresent() ){
            User newUser = new User(userId);
            userRepository.save(newUser);
        }
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
}
