package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Genre;
import com.ra.base_spring_boot.repository.IGenreRepository;
import com.ra.base_spring_boot.services.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService {

    private final IGenreRepository genreRepository;

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
        // Không cho thêm thể loại trùng tên
        if (genreRepository.existsByGenreNameIgnoreCase(genre.getGenreName())) {
            throw new RuntimeException("Thể loại '" + genre.getGenreName() + "' đã tồn tại!");
        }
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Integer id, Genre genre) {
        Genre existing = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại có id = " + id));

        // Kiểm tra trùng tên (ngoại trừ chính nó)
        if (genreRepository.existsByGenreNameIgnoreCase(genre.getGenreName())
                && !existing.getGenreName().equalsIgnoreCase(genre.getGenreName())) {
            throw new RuntimeException("Tên thể loại '" + genre.getGenreName() + "' đã tồn tại!");
        }

        existing.setGenreName(genre.getGenreName());
        return genreRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy thể loại có id = " + id);
        }
        genreRepository.deleteById(id);
    }
}
