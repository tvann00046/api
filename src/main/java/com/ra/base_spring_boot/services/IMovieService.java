package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Movie;
import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> findAll();

    Optional<Movie> findById(Integer id);

    Movie save(Movie movie);

    Movie update(Integer id, Movie movie);

    void delete(Integer id);
}
