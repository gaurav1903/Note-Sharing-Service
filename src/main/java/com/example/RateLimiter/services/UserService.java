package com.example.RateLimiter.services;

public interface UserService {

    String register(String email, String encryptedPass);

    String login(String email, String encryptedPass);
}
