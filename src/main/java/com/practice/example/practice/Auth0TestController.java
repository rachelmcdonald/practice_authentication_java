package com.practice.example.practice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth0")
public class Auth0TestController {

    @GetMapping(value = "/public")
    public ResponseEntity<ResponseDTO> publicEndpoint() {
        return ResponseEntity.ok(new ResponseDTO("Public Endpoint Working fine!"));
    }

    @GetMapping(value = "/private")
    public ResponseEntity<ResponseDTO> privateEndPoint() {
        return ResponseEntity.ok(new ResponseDTO("Private Endpoint Working fine !"));
    }

}
