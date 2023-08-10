package com.example.demo.service;

import com.example.demo.model.Library;
import com.example.demo.model.Song;
import com.example.demo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public void scanAndAddNewSongs(Library library) {
        String libraryLocationPath = library.getLocation();

        try {
            Files.walk(Paths.get(libraryLocationPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> isSupportedSongFile(path.toString()))
                    .forEach(songFile -> {
                        String songLocation = songFile.toString();
                        String songName = getSongNameFromLocation(songLocation);
                        String fileName = songFile.getFileName().toString();
                        Song song = new Song();
                        song.setSongName(songName);
                        song.setFileName(fileName);
                        song.setSongLocation(songLocation);
                        song.setLibrary(library);
                        songRepository.save(song);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Song getSongById(Long id) {
        Optional<Song> optional = songRepository.findById(id);
        Song song = null;
        if(optional.isPresent()) {
            song = optional.get();
        }
        else {
            throw new RuntimeException("Song not found for id ::"+id);
        }
        return song;
    }

    private boolean isSupportedSongFile(String fileName) {
        return fileName.endsWith(".mp3") || fileName.endsWith(".wav");
    }

    private String getSongNameFromLocation(String location) {
        // Implement logic to extract song name from location
        // You can use regular expressions or other methods to extract the song name
        // For example, if your songs are stored in the format "artist - song.mp3":
        // You can split the location using "-" and remove the file extension
        String[] parts = location.split("-");
        if (parts.length > 1) {
            String songNameWithExtension = parts[1];
            return songNameWithExtension.substring(0, songNameWithExtension.lastIndexOf("."));
        } else {
            // If you don't have a specific naming pattern, you might return the whole file name
            return location.substring(location.lastIndexOf("/") + 1, location.lastIndexOf("."));
        }
    }
}
