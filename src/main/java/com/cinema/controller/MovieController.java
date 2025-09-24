package com.cinema.controller;

import com.cinema.entity.Movie;
import com.cinema.entity.Ticket;
import com.cinema.service.MovieService;
import com.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:5173") // 允许前端访问
public class MovieController {

    private final MovieService movieService;
    private final TicketService ticketService;

    public MovieController(MovieService movieService, TicketService ticketService) {
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    // 查询全部电影
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // 查询单个电影
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        try {
            Movie movie = movieService.getMovie(id).orElse(null);
            if (movie == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("电影不存在");
            }
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }

    // 查询座位信息
    @GetMapping("/{id}/seats")
    public ResponseEntity<?> getSeats(@PathVariable Long id) {
        try {
            int[] seatflag = new int[100]; // 假设最多100个座位
            Arrays.fill(seatflag, 0);

            List<Ticket> tickets = ticketService.getTicketsByMovie(id);
            for (Ticket t : tickets) {
                int seatNumber = t.getSeatNumber();
                if (seatNumber >= 1 && seatNumber <= 100) {
                    seatflag[seatNumber - 1] = 1; // 标记已占座位
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("seatflag", seatflag);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }
}