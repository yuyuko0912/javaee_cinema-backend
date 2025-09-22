package com.cinema.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "name_english")
    private String nameEnglish;

    @Column(name = "copy_right")
    private String copyRight;

    @Column(name = "film_img")
    private String filmImg;

    @Column(name = "story_type")
    private String storyType;

    private String place;

    @Column(name = "time_length")
    private Integer timeLength;  // 分钟数

    @Column(name = "time_show")
    private LocalDate timeShow;  // 上映日期

    private String cinema;

    private String room;

    @Column(name = "show_time")
    private LocalDateTime showTime;  // 场次具体时间

    @Column(name = "unit_price")
    private Double unitPrice;

    // ===== 构造方法 =====
    public Movie() {
    }

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

    // ===== Getter 和 Setter =====
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

    // ===== toString =====
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
