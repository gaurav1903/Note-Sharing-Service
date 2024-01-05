package com.example.RateLimiter.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @NonNull
    @Id
    String email;

    @NonNull
    String encryptedPass;
}
