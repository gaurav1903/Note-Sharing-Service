package com.example.RateLimiter.controllers;

import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.Note;
import com.example.RateLimiter.services.NoteService;
import com.example.RateLimiter.services.UserService;
import com.example.RateLimiter.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;
    @GetMapping("/notes")
    public ResponseEntity<?> getAllNotes(@RequestHeader(name = "Authorization")String auth)
    {
        if(userService.verifyUser(auth).equals(ApplicationConstants.TOKEN_VALIDATED))
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<?> getNote(@PathVariable String noteId,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getNoteByQuery(@RequestParam(name = "q") String q,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @PostMapping("/notes")
    public ResponseEntity<?> addNote(@RequestBody Note note,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable String id,@RequestBody Note note,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/notes/{id}/share")
    public ResponseEntity<?> addNote(@PathVariable String id,@RequestHeader(name = "Authorization")String auth)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
