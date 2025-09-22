package com.cinema.service;

import com.cinema.entity.Movie;
import com.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // 查询单个电影
    public Optional<Movie> getMovie(Long id) {
        return movieRepository.findById(id);
    }

    // 查询全部电影
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
