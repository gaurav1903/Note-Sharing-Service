package com.example.RateLimiter.services;

import com.example.RateLimiter.models.User;

public interface UserService {

    String register(User user);

    String login(User user);

    String verifyUserAndRefreshToken(String auth);

    public String getEmailFromVerifiedToken(String auth);
}
