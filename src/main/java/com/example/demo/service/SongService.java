package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.model.Library;

import java.util.List;

public interface SongService {

    void saveSong(Song song);

    List<Song> getAllSongs();

    void scanAndAddNewSongs(Library library);

    Song getSongById(Long id);
}
