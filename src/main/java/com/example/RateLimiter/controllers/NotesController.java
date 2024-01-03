package com.example.RateLimiter.controllers;

import com.example.RateLimiter.models.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes()
    {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<?> getNote(@PathVariable String noteId)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getNoteByQuery(@RequestParam(name = "q") String q)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
