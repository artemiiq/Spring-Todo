package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note_list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam("id") long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "note_edit";
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam("id") long id, 
                            @RequestParam("title") String title, 
                            @RequestParam("content") String content) {
        Note note = new Note(id, title, content);
        noteService.update(note);
        return "redirect:/note/list";
    }

    @PostMapping("/add")
    public String addNote(@RequestParam("title") String title, 
                            @RequestParam("content") String content) {
        Note note = new Note(0, title, content); // ID генерується в сервісі
        noteService.add(note);
        return "redirect:/note/list";
    }
}
