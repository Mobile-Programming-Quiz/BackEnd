package com.quiz_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // 사용자 이름
    private int score;       // 점수
    private LocalDate date;  // 날짜
    private String schoolName; // 학교 이름 (null일 경우 전체 랭킹)

    // 기본 생성자
    public Ranking() {}

    // 생성자
    public Ranking(String username, int score, LocalDate date, String schoolName) {
        this.username = username;
        this.score = score;
        this.date = date;
        this.schoolName = schoolName;  // null일 경우 전체 랭킹
    }
}
