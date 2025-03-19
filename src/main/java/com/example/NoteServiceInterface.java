package com.example;

import java.util.List;

public interface NoteServiceInterface {
    List<Note> listAll();

    Note add(Note note);

    void deleteById(long id);

    void update(Note note);
    
    Note getById(long id);
}
