package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.MusicPlayerService;
import com.example.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MusicPlayerController {

    @Autowired
    private SongService songService;

    @Autowired
    private MusicPlayerService musicPlayerService;

    @GetMapping("/playSong/{songId}")
    public String playSong(@PathVariable Long songId, Model model) {
        Song song = songService.getSongById(songId);
        musicPlayerService.playSong(song);
        return "redirect:/songs";
    }

    @GetMapping("/pauseSong")
    public String pauseSong() {
        musicPlayerService.pauseSong();
        return "redirect:/songs";
    }

    @GetMapping("/stopSong")
    public String stopSong() {
        musicPlayerService.stopSong();
        return "redirect:/songs";
    }
}
