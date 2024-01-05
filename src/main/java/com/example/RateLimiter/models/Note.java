package com.example.RateLimiter.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.Date;

@Data
public class Note {
    @Id
    String id;
    String ownerEmail;

    @TextIndexed
    String data;
    Date createdDate;
}
