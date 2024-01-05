package com.example.RateLimiter.services.impl;

import com.example.RateLimiter.constants.ApplicationConstants;
import com.example.RateLimiter.models.Note;
import com.example.RateLimiter.models.NoteDTO;
import com.example.RateLimiter.models.User;
import com.example.RateLimiter.repositories.NotesRepository;
import com.example.RateLimiter.repositories.UserRepository;
import com.example.RateLimiter.services.NoteService;
import com.example.RateLimiter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
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
        return notesRepository.searchByQueryAndOwnerEmail(query,userService.getEmailFromVerifiedToken(auth));
//    return  new ArrayList<>();
    }


    @Override
    public void addNote(String auth, NoteDTO note1) {
        Note note=new Note();
        note.setData(note1.getData());
        note.setCreatedDate(new Date());
        note.setOwnerEmail(userService.getEmailFromVerifiedToken(auth));
        notesRepository.save(note);
    }

    @Override
    public String updateNote(String auth, NoteDTO note, String noteId) {
        Optional<Note>note1=notesRepository.findById(noteId);
        if(!note1.isEmpty() && note1.get().getOwnerEmail().equalsIgnoreCase(userService.getEmailFromVerifiedToken(auth)))
        {
            note1.get().setData(note.getData());
            notesRepository.save(note1.get());
            return ApplicationConstants.OPERATION_SUCCESSFUL;
        }
        else
            return ApplicationConstants.OPERATION_FAILED;
    }

    @Override
    public String deleteNote(String auth, String noteId) {
        Optional<Note>note1=notesRepository.findById(noteId);
        if(!note1.isEmpty() && note1.get().getOwnerEmail().equalsIgnoreCase(userService.getEmailFromVerifiedToken(auth)))
        {
            notesRepository.delete(note1.get());
            return ApplicationConstants.OPERATION_SUCCESSFUL;
        }
        return ApplicationConstants.OPERATION_FAILED;
    }

    @Override
    public String shareNote(String auth, String noteId, String userId) {
        Optional<Note> note1=notesRepository.findById(noteId);
        Optional<User> user=userRepository.findById(userId);
        if(note1.isPresent() && note1.get().getOwnerEmail().equalsIgnoreCase(userService.getEmailFromVerifiedToken(auth)) && user.isPresent())
        {
            Note note=new Note();
            note.setData(note1.get().getData());
            note.setOwnerEmail(user.get().getEmail());
            note.setCreatedDate(new Date());
            notesRepository.save(note);
            return ApplicationConstants.OPERATION_SUCCESSFUL;
        }
        return ApplicationConstants.OPERATION_FAILED;
    }


}
