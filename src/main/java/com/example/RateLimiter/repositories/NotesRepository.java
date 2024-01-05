package com.example.RateLimiter.repositories;

import com.example.RateLimiter.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Note,String> {
    List<Note> findByOwnerEmail(String email);

    @Query("{$text:  {$search: ?0 }, 'email': ?1 }")
    List<Note> searchByQueryAndOwnerEmail(String pat, String email);
}
