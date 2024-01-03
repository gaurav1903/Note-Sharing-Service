package com.example.RateLimiter.models;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    String id;
    String ownerId;
    String data;
}
