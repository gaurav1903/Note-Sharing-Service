package com.example.RateLimiter.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> login()
    {
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup()
    {
        return new ResponseEntity<>("",HttpStatus.OK);
    }

}
