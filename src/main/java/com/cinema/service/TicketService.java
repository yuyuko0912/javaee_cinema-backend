package com.cinema.service;

import com.cinema.entity.Ticket;
import com.cinema.entity.Movie;
import com.cinema.repository.TicketRepository;
import com.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;

    public TicketService(TicketRepository ticketRepository, MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
    }

    // 根据电影查询票
    public List<Ticket> getTicketsByMovie(Long movieId) {
        return ticketRepository.findByMovie_Id(movieId);
    }

    // 保存票
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // 买票逻辑：检查座位是否被占用 → 保存
    public Ticket buyTicket(Long movieId, int seatNumber, String buyerName) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("电影不存在"));

        boolean occupied = ticketRepository.findByMovie_Id(movieId).stream()
                .anyMatch(t -> t.getSeatNumber() == seatNumber);

        if (occupied) {
            throw new RuntimeException("座位已被占用");
        }

        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setSeatNumber(seatNumber);
        ticket.setBuyerName(buyerName);

        return ticketRepository.save(ticket);
    }
}
