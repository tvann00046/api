package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Movie;
import com.ra.base_spring_boot.repository.IMovieRepository;
import com.ra.base_spring_boot.services.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final IMovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie save(Movie movie) {
        if (movieRepository.existsByTitleAndAuthorIgnoreCase(movie.getTitle(), movie.getAuthor())) {
            throw new RuntimeException("Phim '" + movie.getTitle() + "' của tác giả '" + movie.getAuthor() + "' đã tồn tại!");
        }
        validateMovie(movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Integer id, Movie movie) {
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phim có id = " + id));
        if (movieRepository.existsByTitleAndAuthorIgnoreCase(movie.getTitle(), movie.getAuthor())
                && !existing.getTitle().equalsIgnoreCase(movie.getTitle())) {
            throw new RuntimeException("Phim '" + movie.getTitle() + "' đã tồn tại!");
        }

        validateMovie(movie);

        existing.setTitle(movie.getTitle());
        existing.setDescriptions(movie.getDescriptions());
        existing.setAuthor(movie.getAuthor());
        existing.setImage(movie.getImage());
        existing.setTrailer(movie.getTrailer());
        existing.setType(movie.getType());
        existing.setDuration(movie.getDuration());
        existing.setReleaseDate(movie.getReleaseDate());
        existing.setGenres(movie.getGenres());

        return movieRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy phim có id = " + id);
        }
        movieRepository.deleteById(id);
    }

    private void validateMovie(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Tiêu đề phim không được để trống");
        }
        if (movie.getDuration() == null || movie.getDuration() <= 0) {
            throw new RuntimeException("Thời lượng phim phải lớn hơn 0 phút");
        }
        if (movie.getReleaseDate() == null) {
            throw new RuntimeException("Phải có ngày phát hành");
        }
    }
}
