package com.example.demo.service;

import com.example.demo.model.Song;
import org.springframework.stereotype.Service;

@Service
public class MusicPlayerService {

    private Song currentlyPlayingSong;

    public void playSong(Song song) {
        // Logic to play the song
        if (currentlyPlayingSong != null) {
            stopSong();
        }

        System.out.println("Playing song: " + song.getSongName());
        song.setTimesPlayed(song.getTimesPlayed() + 1);
        currentlyPlayingSong = song;
    }

    public void pauseSong() {
        // Logic to pause the currently playing song
        if (currentlyPlayingSong != null) {
            System.out.println("Pausing song: " + currentlyPlayingSong.getSongName());
        }
    }

    public void stopSong() {
        // Logic to stop the currently playing song
        if (currentlyPlayingSong != null) {
            System.out.println("Stopping song: " + currentlyPlayingSong.getSongName());
            currentlyPlayingSong = null;
        }
    }






}
