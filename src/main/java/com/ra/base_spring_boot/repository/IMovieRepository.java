package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByTitleAndAuthorIgnoreCase(String title, String author);
}
