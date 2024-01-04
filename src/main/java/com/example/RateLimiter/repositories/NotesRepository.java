package com.example.RateLimiter.repositories;

import com.example.RateLimiter.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Note,String> {
}
