package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Genre;
import java.util.List;
import java.util.Optional;

public interface IGenreService {
    List<Genre> findAll();

    Optional<Genre> findById(Integer id);

    Genre save(Genre genre);

    Genre update(Integer id, Genre genre);

    void delete(Integer id);
}
