package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Artist;

public interface ArtistService {
	List<Artist> getAllArtists();
	void saveArtist(Artist artist);
	Artist getArtistById(long id);
	void deleteArtistById(long id);
}
