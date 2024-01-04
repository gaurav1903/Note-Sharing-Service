package com.example.RateLimiter.services.impl;

import com.example.RateLimiter.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String register(String email, String encryptedPass) {
        return null;
    }

    @Override
    public String login(String email, String encryptedPass) {
        return null;
    }
}
