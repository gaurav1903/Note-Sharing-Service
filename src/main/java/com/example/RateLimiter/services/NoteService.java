package com.example.RateLimiter.services;

import com.example.RateLimiter.models.Note;
import com.example.RateLimiter.models.NoteDTO;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes(String auth);

    List<Note> getNote(String auth,String id);

    List<Note> getNoteByQuery(String auth,String query);

    void addNote(String auth, NoteDTO note);

    String updateNote(String auth,NoteDTO note,String noteId);


    String deleteNote(String auth,String noteId);

    String shareNote(String auth, String noteId, String userId);

}
