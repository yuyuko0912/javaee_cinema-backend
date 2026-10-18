package com.cinema.service;

import com.cinema.entity.Movie;
import com.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 电影领域业务服务。
 * <p>
 * 封装对 {@link MovieRepository} 的访问，供 {@link com.cinema.controller.MovieController} 调用。
 * </p>
 *
 * @author cinema
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * @param movieRepository 电影数据访问层
     */
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * 按主键查询电影。
     *
     * @param id 电影 ID
     * @return 存在则 {@link Optional#of}，否则 {@link Optional#empty}
     */
    public Optional<Movie> getMovie(Long id) {
        return movieRepository.findById(id);
    }

    /**
     * 查询全部电影记录。
     *
     * @return 电影列表，无数据时返回空列表
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
