package com.example.RateLimiter.controllers;

import com.example.RateLimiter.configs.RateLimiterConfig;
import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.NoteDTO;
import com.example.RateLimiter.models.ResponseModel;
import com.example.RateLimiter.services.NoteService;
import com.example.RateLimiter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    NoteService noteService;

    @Autowired
    RateLimiterConfig rateLimiterConfig;

    @Autowired
    UserService userService;
    @GetMapping(value = "/notes", produces = "application/json")
    public ResponseEntity<?> getAllNotes(@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.getAllNotes(auth)), HttpStatus.OK);
            }

            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<?> getNote(@PathVariable String noteId,@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.getNote(auth, noteId)), HttpStatus.OK);
            }

            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getNoteByQuery(@RequestParam(name = "q") String q,@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.getNoteByQuery(auth, q)), HttpStatus.OK);
            }
            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }
    @PostMapping(value = "/notes")
    public ResponseEntity<?> addNote(@RequestBody NoteDTO note, @RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED) && note.getData() != null) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());
                noteService.addNote(auth, note);
                String answer = ApplicationConstants.OPERATION_SUCCESSFUL;
                ResponseModel model = new ResponseModel(refreshedAuth, answer);
                return new ResponseEntity<>(model, HttpStatus.OK);
            }
            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable String id,@RequestBody NoteDTO note,@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.updateNote(auth, note, id)), HttpStatus.OK);
            }
            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id,@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.deleteNote(auth, id)), HttpStatus.OK);
            }
            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/notes/{userEmail}/share")
    public ResponseEntity<?> shareNote(@PathVariable String userEmail,@RequestParam(name = "noteId") String noteId,@RequestHeader(name = "Authorization")String auth)
    {
        if(rateLimiterConfig.rateLimiter().tryAcquire()) {
            String token = userService.verifyUserAndRefreshToken(auth);
            if (token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
                String refreshedAuth = token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

                return new ResponseEntity<>(new ResponseModel(refreshedAuth, noteService.shareNote(auth, noteId, userEmail)), HttpStatus.OK);
            }
            return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
        }
        else
            return  new ResponseEntity<>(ApplicationConstants.TOO_MANY_REQUESTS, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
