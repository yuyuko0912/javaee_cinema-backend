package com.cinema.repository;

import com.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 电影（场次）数据访问接口。
 * <p>
 * 继承 {@link JpaRepository} 后自动提供按主键增删改查等方法；
 * Service 层通过本接口访问 {@code movie} 表，无需手写 SQL。
 * </p>
 *
 * @author cinema
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
