package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/note")
class NoteController {
    private final List<Note> notes = new ArrayList<>();

    @GetMapping("/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", notes);
        return "note_list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") int id) {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam("id") int id, Model model) {
        for (Note note : notes) {
            if (note.getId() == id) {
                model.addAttribute("note", note);
                break;
            }
        }
        return "note_edit";
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        for (Note note : notes) {
            if (note.getId() == id) {
                note.setTitle(title);
                note.setContent(content);
                break;
            }
        }
        return "redirect:/note/list";
    }

    @PostMapping("/add")
public String addNote(@RequestParam("id") long id, 
                    @RequestParam("title") String title, 
                    @RequestParam("content") String content) {
    notes.add(new Note(id, title, content));
    return "redirect:/note/list";
    }
}