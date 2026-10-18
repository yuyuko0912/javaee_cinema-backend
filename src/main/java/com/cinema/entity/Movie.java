package com.cinema.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 电影（场次）实体，对应数据库表 {@code movie}。
 * <p>
 * 一条记录表示某影院某厅的一次放映场次，包含片名、海报、时长、票价等信息。
 * </p>
 *
 * @author cinema
 */
@Entity
@Table(name = "movie")
public class Movie {

    /** 主键，自增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 中文片名 */
    private String name;

    /** 英文片名 */
    @Column(name = "name_english")
    private String nameEnglish;

    /** 版权/发行信息 */
    @Column(name = "copy_right")
    private String copyRight;

    /** 海报图片 URL */
    @Column(name = "film_img")
    private String filmImg;

    /** 影片类型（如动作、喜剧） */
    @Column(name = "story_type")
    private String storyType;

    /** 地区/产地 */
    private String place;

    /** 片长，单位：分钟 */
    @Column(name = "time_length")
    private Integer timeLength;

    /** 上映日期 */
    @Column(name = "time_show")
    private LocalDate timeShow;

    /** 影院名称 */
    private String cinema;

    /** 影厅名称或编号 */
    private String room;

    /** 本场放映开始时间 */
    @Column(name = "show_time")
    private LocalDateTime showTime;

    /** 单张票价 */
    @Column(name = "unit_price")
    private Double unitPrice;

    /** JPA 要求的无参构造 */
    public Movie() {
    }

    /**
     * 便于测试或手工构造场次的全参构造（不含 id）。
     */
    public Movie(String name, String nameEnglish, String copyRight, String filmImg,
                 String storyType, String place, Integer timeLength, LocalDate timeShow,
                 String cinema, String room, LocalDateTime showTime, Double unitPrice) {
        this.name = name;
        this.nameEnglish = nameEnglish;
        this.copyRight = copyRight;
        this.filmImg = filmImg;
        this.storyType = storyType;
        this.place = place;
        this.timeLength = timeLength;
        this.timeShow = timeShow;
        this.cinema = cinema;
        this.room = room;
        this.showTime = showTime;
        this.unitPrice = unitPrice;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNameEnglish() { return nameEnglish; }
    public void setNameEnglish(String nameEnglish) { this.nameEnglish = nameEnglish; }

    public String getCopyRight() { return copyRight; }
    public void setCopyRight(String copyRight) { this.copyRight = copyRight; }

    public String getFilmImg() { return filmImg; }
    public void setFilmImg(String filmImg) { this.filmImg = filmImg; }

    public String getStoryType() { return storyType; }
    public void setStoryType(String storyType) { this.storyType = storyType; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public Integer getTimeLength() { return timeLength; }
    public void setTimeLength(Integer timeLength) { this.timeLength = timeLength; }

    public LocalDate getTimeShow() { return timeShow; }
    public void setTimeShow(LocalDate timeShow) { this.timeShow = timeShow; }

    public String getCinema() { return cinema; }
    public void setCinema(String cinema) { this.cinema = cinema; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public LocalDateTime getShowTime() { return showTime; }
    public void setShowTime(LocalDateTime showTime) { this.showTime = showTime; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameEnglish='" + nameEnglish + '\'' +
                ", copyRight='" + copyRight + '\'' +
                ", filmImg='" + filmImg + '\'' +
                ", storyType='" + storyType + '\'' +
                ", place='" + place + '\'' +
                ", timeLength=" + timeLength +
                ", timeShow=" + timeShow +
                ", cinema='" + cinema + '\'' +
                ", room='" + room + '\'' +
                ", showTime=" + showTime +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
