package com.ra.base_spring_boot.model;

import jakarta.persistence.*;


@Entity
@Table(name = "movie_genre")
@IdClass(MovieGenreId.class)
public class MovieGenre {

    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Id
    @Column(name = "genre_id")
    private Integer genreId;

}
