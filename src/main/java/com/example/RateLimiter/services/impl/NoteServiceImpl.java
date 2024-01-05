package com.example.RateLimiter.services.impl;

import com.example.RateLimiter.models.Note;
import com.example.RateLimiter.repositories.NotesRepository;
import com.example.RateLimiter.services.NoteService;
import com.example.RateLimiter.services.UserService;
import com.example.RateLimiter.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    UserService userService;
    @Override
    public List<Note> getAllNotes(String auth) {

        return notesRepository.findByOwnerEmail(userService.getEmailFromVerifiedToken(auth));
    }

    @Override
    public Note getNote(String auth,String id) {
        Optional<Note> note=notesRepository.findById(id);
        if(note.isPresent())
        {
            if(note.get().getOwnerEmail().equalsIgnoreCase(userService.getEmailFromVerifiedToken(auth)))
                return note.get();
        }
        return new Note();
    }

    @Override
    public List<Note> getNoteByQuery(String auth, String query) {

    }

}
