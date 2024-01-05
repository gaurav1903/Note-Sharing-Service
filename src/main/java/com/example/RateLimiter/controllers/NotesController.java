package com.example.RateLimiter.controllers;

import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.Note;
import com.example.RateLimiter.models.NoteDTO;
import com.example.RateLimiter.models.ResponseModel;
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
    NoteService noteService;

    @Autowired
    UserService userService;
    @GetMapping("/notes")
    public ResponseEntity<?> getAllNotes(@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.getAllNotes(auth)), HttpStatus.OK);
        }

        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<?> getNote(@PathVariable String noteId,@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.getNote(auth,noteId)), HttpStatus.OK);
        }

        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getNoteByQuery(@RequestParam(name = "q") String q,@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.getNoteByQuery(auth,q)), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/notes")
    public ResponseEntity<?> addNote(@RequestBody NoteDTO note, @RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());
            noteService.addNote(auth,note);
            return new ResponseEntity<>(new ResponseModel(refreshedAuth,ApplicationConstants.OPERATION_SUCCESSFUL), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable String id,@RequestBody NoteDTO note,@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.updateNote(auth,note,id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable String id,@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.deleteNote(auth,id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/notes/{userId}/share")
    public ResponseEntity<?> shareNote(@PathVariable String userId,@RequestParam(name = "noteId") String noteId,@RequestHeader(name = "Authorization")String auth)
    {
        String token=userService.verifyUserAndRefreshToken(auth);
        if(token.startsWith(ApplicationConstants.TOKEN_VALIDATED)) {
            String refreshedAuth=token.substring(ApplicationConstants.TOKEN_VALIDATED.length());

            return new ResponseEntity<>(new ResponseModel(refreshedAuth,noteService.shareNote(auth,noteId,userId)), HttpStatus.OK);
        }
        return new ResponseEntity<>(ApplicationConstants.INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

}
