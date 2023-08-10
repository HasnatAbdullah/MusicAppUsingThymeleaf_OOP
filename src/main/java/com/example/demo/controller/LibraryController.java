package com.example.demo.controller;

import com.example.demo.model.Library;
import com.example.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/library")
    public String viewLibraryPage(Model model) {
        model.addAttribute("libraries", libraryService.getAllLibraries());
        return "library";
    }

    @GetMapping("/showNewLibraryForm")
    public String showNewLibraryForm(Model model) {
        Library library = new Library();
        model.addAttribute("library", library);
        return "new_library";
    }

    @PostMapping("/saveLibrary")
    public String saveLibrary(@ModelAttribute("library") Library library) {
        libraryService.saveLibrary(library);
        return "redirect:/library";
    }

    // Similar methods for update and delete

    @GetMapping("/showLibraryFormForUpdate/{id}")
    public String showLibraryFormForUpdate(@PathVariable (value="id") long id, Model model) {
        Library library = libraryService.getLibraryById(id);
        model.addAttribute("library", library);
        return "update_library";
    }

    @GetMapping("/deleteLibrary/{id}")
    public String deleteLibrary(@PathVariable (value= "id") long id) {
        this.libraryService.deleteLibraryById(id);
        return "redirect:/";
    }
}
