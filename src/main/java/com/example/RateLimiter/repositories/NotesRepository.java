package com.example.RateLimiter.repositories;

import com.example.RateLimiter.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Note,String> {
    List<Note> findByOwnerEmail(String email);
}
