package com.example.demo.service;

import com.example.demo.model.Library;
import com.example.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @Override
    public void saveLibrary(Library library) {
        libraryRepository.save(library);
    }

    @Override
    public Library getLibraryById(Long id) {
        Optional<Library> optionalLibrary = libraryRepository.findById(id);
        return optionalLibrary.orElse(null);
    }

    @Override
    public void deleteLibraryById(Long id) {
        libraryRepository.deleteById(id);
    }

}