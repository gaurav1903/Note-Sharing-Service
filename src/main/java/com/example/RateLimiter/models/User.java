package com.example.RateLimiter.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {
    @NonNull
    @Id
    String email;

    @NonNull
    String encryptedPass;
}
