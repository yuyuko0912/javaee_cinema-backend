package com.cinema.repository;

import com.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // 根据 Movie 的 id 查询票
    List<Ticket> findByMovie_Id(Long movieId);

}
