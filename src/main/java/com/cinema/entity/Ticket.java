package com.cinema.entity;

import jakarta.persistence.*;

/**
 * 票务实体，对应数据库表 {@code ticket}。
 * <p>
 * 一张票绑定一个场次（{@link Movie}）和一个座位号，并记录购票人姓名。
 * </p>
 *
 * @author cinema
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    /** 主键，自增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 所属场次；多票对应一场电影 */
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    /** 座位编号，业务约定为 1–100 的整数 */
    private int seatNumber;

    /** 购票人姓名，用于展示及每人每场限购统计 */
    private String buyerName;

    /** JPA 要求的无参构造 */
    public Ticket() {}

    /**
     * @param movie      所属场次
     * @param seatNumber 座位号
     * @param buyerName  购票人
     */
    public Ticket(Movie movie, int seatNumber, String buyerName) {
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.buyerName = buyerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
