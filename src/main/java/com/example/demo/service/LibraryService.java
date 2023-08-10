package com.example.demo.service;

import com.example.demo.model.Library;

import java.util.List;

public interface LibraryService {
    List<Library> getAllLibraries();
    void saveLibrary(Library library);
    Library getLibraryById(Long id);
    void deleteLibraryById(Long id);

}