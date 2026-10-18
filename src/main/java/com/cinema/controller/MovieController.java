package com.cinema.controller;

import com.cinema.entity.Movie;
import com.cinema.entity.Ticket;
import com.cinema.service.MovieService;
import com.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 电影相关 REST 接口。
 * <p>
 * 提供电影列表、详情及场次座位占用状态的查询，供前端展示与选座使用。
 * </p>
 *
 * @author cinema
 */
@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    private final MovieService movieService;
    private final TicketService ticketService;

    /**
     * 构造注入电影与票务服务。
     *
     * @param movieService  电影业务服务
     * @param ticketService 票务业务服务
     */
    public MovieController(MovieService movieService, TicketService ticketService) {
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    /**
     * 查询全部在映电影。
     *
     * @return 电影列表，按数据库默认顺序返回
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * 根据 ID 查询单部电影详情。
     *
     * @param id 电影主键
     * @return 200 与电影实体；404 若不存在；500 系统异常
     */
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

    /**
     * 查询指定场次的座位占用情况。
     * <p>
     * 返回长度为 100 的数组 {@code seatflag}：下标 i 对应座位号 i+1，0 表示空闲，1 表示已售。
     * </p>
     *
     * @param id 电影（场次）主键
     * @return JSON {@code { "seatflag": int[100] }}；失败时 500
     */
    @GetMapping("/{id}/seats")
    public ResponseEntity<?> getSeats(@PathVariable Long id) {
        try {
            int[] seatflag = new int[100];
            Arrays.fill(seatflag, 0);

            List<Ticket> tickets = ticketService.getTicketsByMovie(id);
            for (Ticket t : tickets) {
                int seatNumber = t.getSeatNumber();
                if (seatNumber >= 1 && seatNumber <= 100) {
                    seatflag[seatNumber - 1] = 1;
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
