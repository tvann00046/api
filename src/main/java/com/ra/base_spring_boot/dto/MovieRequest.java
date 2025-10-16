package com.ra.base_spring_boot.dto;

import com.ra.base_spring_boot.model.MovieType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class MovieRequest {
    private String title;
    private String descriptions;
    private String author;
    private String image;
    private String trailer;
    private MovieType type;
    private Integer duration;
    private LocalDateTime releaseDate;
    private Set<Integer> genreIds;
}