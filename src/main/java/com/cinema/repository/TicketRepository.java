package com.cinema.repository;

import com.cinema.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 票务数据访问接口。
 * <p>
 * 除 JPA 通用 CRUD 外，按场次、座位、购票人提供查询，供占座校验与限购统计使用。
 * </p>
 *
 * @author cinema
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * 查询某场次的全部已售票。
     *
     * @param movieId 电影（场次）主键
     * @return 票务列表
     */
    List<Ticket> findByMovie_Id(Long movieId);

    /**
     * 判断指定场次下某座位是否已有票（用于购票前校验）。
     *
     * @param movieId    场次 ID
     * @param seatNumber 座位号
     * @return 已存在则为 {@code true}
     */
    boolean existsByMovie_IdAndSeatNumber(Long movieId, int seatNumber);

    /**
     * 统计某购票人在某场次已购张数（限购规则）。
     *
     * @param movieId   场次 ID
     * @param buyerName 购票人姓名
     * @return 已购数量
     */
    int countByMovie_IdAndBuyerName(Long movieId, String buyerName);
}
