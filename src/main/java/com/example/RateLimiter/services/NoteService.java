package com.example.RateLimiter.services;

import com.example.RateLimiter.models.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes(String auth);

    Note getNote(String auth,String id);

    List<Note> getNoteByQuery(String auth,String query);

}
