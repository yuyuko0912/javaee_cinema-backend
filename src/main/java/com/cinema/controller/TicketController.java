package com.cinema.controller;

import com.cinema.entity.Ticket;
import com.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:5173") // 添加CORS支持
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/movie/{movieId}")
    public List<Ticket> getTicketsByMovie(@PathVariable Long movieId) {
        return ticketService.getTicketsByMovie(movieId);
    }

    @PostMapping("/")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    /**
     * 买票接口（单张）
     * POST /tickets/buy
     * body: { "movieId": 1, "seatNumber": 10, "buyerName": "张三" }
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
            // 返回明确的错误信息，前端可以正确识别
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }

    /**
     * 批量买票接口
     * POST /tickets/buy-batch
     * body: { "movieId": 1, "seatNumbers": [10, 11, 12], "buyerName": "张三" }
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
            // 返回明确的错误信息，前端可以正确识别
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误，请稍后重试");
        }
    }
}