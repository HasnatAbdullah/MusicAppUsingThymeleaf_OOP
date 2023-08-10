package com.example.demo.controller;

import com.example.demo.model.Library;
import com.example.demo.model.Song;
import com.example.demo.service.LibraryService;
import com.example.demo.service.MusicPlayerService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/songs")
    public String listSongs(Model model) {
        List<Library> libraries = libraryService.getAllLibraries();
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        model.addAttribute("libraries", libraries);
        return "song_list";
    }

    @GetMapping("/addSong")
    public String showAddSongForm(Model model) {
        List<Library> libraries = libraryService.getAllLibraries();
        model.addAttribute("libraries", libraries);
        model.addAttribute("song", new Song());
        return "add_song";
    }

    @PostMapping("/saveSong")
    public String saveSong(@ModelAttribute Song song) {
        songService.saveSong(song);
        return "redirect:/songs";
    }

    @PostMapping("/scanLibraryForSongs")
    public String scanLibraryForSongs(@RequestParam("libraryId") Long libraryId) {
        Library library = libraryService.getLibraryById(libraryId);
        if (library != null) {
            songService.scanAndAddNewSongs(library);
        }
        return "redirect:/songs";
    }
}
