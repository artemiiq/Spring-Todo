package com.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Transactional
    public Note add(Note note) {
        return noteRepository.save(note);
    }

    @Transactional
    public void deleteById(long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoSuchElementException("Note not found");
        }
        noteRepository.deleteById(id);
    }

    @Transactional
    public void update(Note note) {
        if (!noteRepository.existsById(note.getId())) {
            throw new NoteNotFoundException("Note with ID " + note.getId() + " not found");
        }
        noteRepository.save(note);
    }

    @Transactional(readOnly = true)
    public Note getById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
    }
}
