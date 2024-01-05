package com.example.RateLimiter.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Note {
    @Id
    String id;
    String ownerEmail;
    String data;
    Date createdDate;
}
