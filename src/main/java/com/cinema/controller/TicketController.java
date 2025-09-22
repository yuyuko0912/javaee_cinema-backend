package com.cinema.controller;

import com.cinema.entity.Ticket;
import com.cinema.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
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
     * 买票接口
     * POST /tickets/buy
     * body: { "movieId": 1, "seatNumber": 10, "buyerName": "张三" }
     */
    @PostMapping("/buy")
    public Ticket buyTicket(@RequestBody Map<String, Object> body) {
        Long movieId = Long.valueOf(body.get("movieId").toString());
        int seatNumber = Integer.parseInt(body.get("seatNumber").toString());
        String buyerName = body.get("buyerName").toString();
        return ticketService.buyTicket(movieId, seatNumber, buyerName);
    }
}
