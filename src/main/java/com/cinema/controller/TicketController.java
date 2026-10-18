package com.cinema.controller;

import com.cinema.entity.Ticket;
import com.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 票务相关 REST 接口。
 * <p>
 * 支持按电影查询已售票、单张/批量购票等操作。
 * </p>
 *
 * @author cinema
 */
@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    private final TicketService ticketService;

    /**
     * 构造注入票务服务。
     *
     * @param ticketService 票务业务服务
     */
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * 查询某场电影的全部已售票记录。
     *
     * @param movieId 电影主键
     * @return 该场次下的票务列表
     */
    @GetMapping("/movie/{movieId}")
    public List<Ticket> getTicketsByMovie(@PathVariable Long movieId) {
        return ticketService.getTicketsByMovie(movieId);
    }

    /**
     * 直接保存一张票务实体（管理或调试用途，一般购票走 {@link #buyTicket}）。
     *
     * @param ticket 待持久化的票务
     * @return 保存后的票务（含生成的主键）
     */
    @PostMapping("/")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    /**
     * 购买单张票。
     *
     * @param body JSON：{@code movieId}、{@code seatNumber}、{@code buyerName}
     * @return 200 与新建票务；400 业务错误（座位占用、限购等）；500 系统错误
     */
    @PostMapping("/buy")
    public ResponseEntity<?> buyTicket(@RequestBody Map<String, Object> body) {
        try {
            Long movieId = Long.valueOf(body.get("movieId").toString());
            int seatNumber = Integer.parseInt(body.get("seatNumber").toString());
            String buyerName = body.get("buyerName").toString();
            Ticket ticket = ticketService.buyTicket(movieId, seatNumber, buyerName);
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }

    /**
     * 批量购买多张票（同一购票人、同一场次）。
     *
     * @param body JSON：{@code movieId}、{@code seatNumbers}（整数数组）、{@code buyerName}
     * @return 200 与票务列表；400 业务错误；500 系统错误
     */
    @PostMapping("/buy-batch")
    public ResponseEntity<?> buyTicketsBatch(@RequestBody Map<String, Object> body) {
        try {
            Long movieId = Long.valueOf(body.get("movieId").toString());

            @SuppressWarnings("unchecked")
            List<Integer> seatNumbers = (List<Integer>) body.get("seatNumbers");

            String buyerName = body.get("buyerName").toString();
            List<Ticket> tickets = ticketService.buyTicketsBatch(movieId, seatNumbers, buyerName);
            return ResponseEntity.ok(tickets);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }
}
