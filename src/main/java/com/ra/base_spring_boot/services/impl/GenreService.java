package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Genre;
import com.ra.base_spring_boot.repository.IGenreRepository;
import com.ra.base_spring_boot.services.IGenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {

    @Autowired
    private IGenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Integer id, Genre genre) {
        genre.setId(id);
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Integer id) {
        genreRepository.deleteById(id);
    }
}
