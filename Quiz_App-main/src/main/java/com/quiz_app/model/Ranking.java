//Ranking 모델은 사용자 이름, 점수, 날짜 및 랭킹 타입(일일/주간)을 포함.
//랭킹 타입은 열거형으로 정의하여 일일 또는 주간 랭킹을 구분합니다.


package com.quiz_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private RankingType rankingType; // DAILY 또는 WEEKLY

    public enum RankingType {
        DAILY, WEEKLY
    }

    // 기본 생성자
    public Ranking() {}

    // 생성자
    public Ranking(String username, int score, LocalDate date, RankingType rankingType) {
        this.username = username;
        this.score = score;
        this.date = date;
        this.rankingType = rankingType;
    }

    // Getters and Setters
    // ...
}
