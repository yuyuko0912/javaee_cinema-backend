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

        // 检查该用户在本场电影中已购买的票数
        int purchasedCount = (int) ticketRepository.findByMovie_Id(movieId).stream()
                .filter(t -> t.getBuyerName().equals(buyerName))
                .count();

        if (purchasedCount >= 5) {
            throw new RuntimeException("每场电影最多只能购买5张票");
        }

        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setSeatNumber(seatNumber);
        ticket.setBuyerName(buyerName);

        return ticketRepository.save(ticket);
    }

    // 批量买票逻辑：一次最多购买5个座位
    public List<Ticket> buyTicketsBatch(Long movieId, List<Integer> seatNumbers, String buyerName) {
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            throw new RuntimeException("请选择座位");
        }

        if (seatNumbers.size() > 5) {
            throw new RuntimeException("一次最多只能购买5个座位");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("电影不存在"));

        // 检查该用户在本场电影中已购买的票数
        int purchasedCount = (int) ticketRepository.findByMovie_Id(movieId).stream()
                .filter(t -> t.getBuyerName().equals(buyerName))
                .count();

        if (purchasedCount + seatNumbers.size() > 5) {
            throw new RuntimeException("每场电影最多只能购买5张票，您已购买" + purchasedCount + "张");
        }

        // 检查座位是否被占用
        List<Ticket> existingTickets = ticketRepository.findByMovie_Id(movieId);
        for (Integer seatNumber : seatNumbers) {
            boolean occupied = existingTickets.stream()
                    .anyMatch(t -> t.getSeatNumber() == seatNumber);
            if (occupied) {
                throw new RuntimeException("座位 " + seatNumber + " 已被占用");
            }
        }

        // 保存所有票
        return seatNumbers.stream()
                .map(seatNumber -> {
                    Ticket ticket = new Ticket();
                    ticket.setMovie(movie);
                    ticket.setSeatNumber(seatNumber);
                    ticket.setBuyerName(buyerName);
                    return ticketRepository.save(ticket);
                })
                .toList();
    }
}