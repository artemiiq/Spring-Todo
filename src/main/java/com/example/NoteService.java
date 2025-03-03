package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private long idCounter = 1;

    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    public Note add(Note note) {
        long id = idCounter++;
        note.setId(id);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        Note note = getById(id);
        notes.remove(note);
    }

    public void update(Note note) {
        Note existingNote = getById(note.getId());
        int index = notes.indexOf(existingNote);
        notes.set(index, note);
    }

    public Note getById(long id) {
        return notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
    }
}