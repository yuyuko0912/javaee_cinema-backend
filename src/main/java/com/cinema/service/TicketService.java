package com.cinema.service;

import com.cinema.entity.Ticket;
import com.cinema.entity.Movie;
import com.cinema.repository.TicketRepository;
import com.cinema.repository.MovieRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * 票务领域业务服务。
 * <p>
 * 负责查票、占座校验、每人每场限购 5 张等业务规则，并持久化票务记录。
 * </p>
 *
 * @author cinema
 */
@Service
public class TicketService {

    /** 同一购票人在单场电影中允许持有的最大票数 */
    private static final int MAX_TICKETS_PER_BUYER_PER_MOVIE = 5;

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;

    /**
     * @param ticketRepository 票务数据访问层
     * @param movieRepository  电影数据访问层（校验场次是否存在）
     */
    public TicketService(TicketRepository ticketRepository, MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * 查询指定场次的全部已售票。
     *
     * @param movieId 电影（场次）主键
     * @return 票务列表
     */
    public List<Ticket> getTicketsByMovie(Long movieId) {
        return ticketRepository.findByMovie_Id(movieId);
    }

    /**
     * 保存或更新票务实体。
     *
     * @param ticket 票务
     * @return 持久化后的实体
     */
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * 购买单张票：校验场次、座位占用及限购后写入数据库。
     * <p>
     * 使用事务 + 数据库唯一约束（场次+座位）防止并发双卖。
     * </p>
     *
     * @param movieId    电影 ID
     * @param seatNumber 座位号（1–100）
     * @param buyerName  购票人姓名（用于限购统计）
     * @return 新建的票务
     * @throws RuntimeException 电影不存在、座位已占用或超出限购
     */
    @Transactional
    public Ticket buyTicket(Long movieId, int seatNumber, String buyerName) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("电影不存在"));

        if (ticketRepository.existsByMovie_IdAndSeatNumber(movieId, seatNumber)) {
            throw new RuntimeException("座位已被占用");
        }

        int purchasedCount = ticketRepository.countByMovie_IdAndBuyerName(movieId, buyerName);
        if (purchasedCount >= MAX_TICKETS_PER_BUYER_PER_MOVIE) {
            throw new RuntimeException("每场电影最多只能购买5张票");
        }

        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setSeatNumber(seatNumber);
        ticket.setBuyerName(buyerName);

        try {
            return ticketRepository.save(ticket);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("座位已被占用");
        }
    }

    /**
     * 批量购票：单次最多 5 个座位，且与已有票数合计不超过每人每场 5 张。
     * <p>
     * 整批在同一事务内提交，任一步失败则全部回滚。
     * </p>
     *
     * @param movieId     电影 ID
     * @param seatNumbers 座位号列表
     * @param buyerName   购票人姓名
     * @return 成功创建的全部票务
     * @throws RuntimeException 参数为空、超量、电影不存在、任一座位已占用或超出限购
     */
    @Transactional
    public List<Ticket> buyTicketsBatch(Long movieId, List<Integer> seatNumbers, String buyerName) {
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            throw new RuntimeException("请选择座位");
        }

        if (seatNumbers.size() > MAX_TICKETS_PER_BUYER_PER_MOVIE) {
            throw new RuntimeException("一次最多只能购买5个座位");
        }

        if (seatNumbers.size() != new HashSet<>(seatNumbers).size()) {
            throw new RuntimeException("不能重复选择同一座位");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("电影不存在"));

        int purchasedCount = ticketRepository.countByMovie_IdAndBuyerName(movieId, buyerName);
        if (purchasedCount + seatNumbers.size() > MAX_TICKETS_PER_BUYER_PER_MOVIE) {
            throw new RuntimeException("每场电影最多只能购买5张票，您已购买" + purchasedCount + "张");
        }

        for (Integer seatNumber : seatNumbers) {
            if (ticketRepository.existsByMovie_IdAndSeatNumber(movieId, seatNumber)) {
                throw new RuntimeException("座位 " + seatNumber + " 已被占用");
            }
        }

        try {
            return seatNumbers.stream()
                    .map(seatNumber -> {
                        Ticket ticket = new Ticket();
                        ticket.setMovie(movie);
                        ticket.setSeatNumber(seatNumber);
                        ticket.setBuyerName(buyerName);
                        return ticketRepository.save(ticket);
                    })
                    .toList();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("部分座位已被其他人购买，请刷新后重选");
        }
    }
}
