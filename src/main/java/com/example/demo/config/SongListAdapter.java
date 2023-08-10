package com.example.demo.config;

import com.example.demo.model.Song;

import java.util.List;

public class SongListAdapter {

    private List<Song> songs;

    public SongListAdapter(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    // Other methods as needed
}