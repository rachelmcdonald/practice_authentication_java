package com.practice.example.practice;

import com.practice.example.practice.models.Task;
import com.practice.example.practice.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth0")
public class Auth0TestController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping(value = "/public")
    public ResponseEntity<ResponseDTO> publicEndpoint() {
        return ResponseEntity.ok(new ResponseDTO("Public Endpoint Working fine!"));
    }

    @GetMapping(value = "/private")
    public ResponseEntity<ResponseDTO> privateEndPoint() {
        return ResponseEntity.ok(new ResponseDTO("Private Endpoint Working fine !"));
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }
}
