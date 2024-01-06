package com.example.RateLimiter.controllers;


import com.example.RateLimiter.configs.RateLimiterConfig;
import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.User;
import com.example.RateLimiter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RateLimiterConfig rateLimiterConfig;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire())
            return new ResponseEntity<>(userService.login(user),HttpStatus.OK);
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire())
            return new ResponseEntity<>(userService.register(user),HttpStatus.OK);
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
