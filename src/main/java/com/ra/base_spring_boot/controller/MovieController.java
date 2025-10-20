package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.dto.MovieRequest;
import com.ra.base_spring_boot.model.Genre;
import com.ra.base_spring_boot.model.Movie;
import com.ra.base_spring_boot.repository.IGenreRepository;
import com.ra.base_spring_boot.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final IMovieRepository movieRepository;
    private final IGenreRepository genreRepository;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest request) {

        if (movieRepository.existsByTitleAndAuthorIgnoreCase(request.getTitle(), request.getAuthor())) {
            return ResponseEntity.badRequest()
                    .body("Phim '" + request.getTitle() + "' của tác giả '" + request.getAuthor() + "' đã tồn tại!");
        }

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Tiêu đề phim không được để trống");
        }
        if (request.getDuration() == null || request.getDuration() <= 0) {
            return ResponseEntity.badRequest().body("Thời lượng phim phải lớn hơn 0 phút");
        }
        if (request.getReleaseDate() == null) {
            return ResponseEntity.badRequest().body("Phải có ngày phát hành phim");
        }

        Set<Genre> genres = request.getGenreIds().stream()
                .map(id -> genreRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id)))
                .collect(Collectors.toSet());

        Movie movie = Movie.builder()
                .title(request.getTitle())
                .descriptions(request.getDescriptions())
                .author(request.getAuthor())
                .image(request.getImage())
                .trailer(request.getTrailer())
                .type(request.getType())
                .duration(request.getDuration())
                .releaseDate(request.getReleaseDate())
                .genres(genres)
                .build();

        return ResponseEntity.ok(movieRepository.save(movie));
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        boolean exists = movieRepository.existsByTitleAndAuthorIgnoreCase(request.getTitle(), request.getAuthor());
        if (exists && !movie.getTitle().equalsIgnoreCase(request.getTitle())) {
            return ResponseEntity.badRequest()
                    .body("Phim '" + request.getTitle() + "' của tác giả '" + request.getAuthor() + "' đã tồn tại!");
        }

        Set<Genre> genres = request.getGenreIds().stream()
                .map(gid -> genreRepository.findById(gid)
                        .orElseThrow(() -> new RuntimeException("Genre not found with id: " + gid)))
                .collect(Collectors.toSet());

        movie.setTitle(request.getTitle());
        movie.setDescriptions(request.getDescriptions());
        movie.setAuthor(request.getAuthor());
        movie.setImage(request.getImage());
        movie.setTrailer(request.getTrailer());
        movie.setType(request.getType());
        movie.setDuration(request.getDuration());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setGenres(genres);

        return ResponseEntity.ok(movieRepository.save(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        if (!movieRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
