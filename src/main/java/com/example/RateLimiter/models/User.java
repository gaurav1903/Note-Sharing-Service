package com.example.RateLimiter.models;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    String email;
    String encryptedPass;
}
