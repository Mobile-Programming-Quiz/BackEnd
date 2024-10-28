package com.quiz_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // 투표 카테고리 (예: "역사", "과학", "문학" 등)

    private int votes; // 해당 카테고리에 대한 투표 수

    // 기본 생성자
    public Vote() {}

    // 생성자
    public Vote(String category, int votes) {
        this.category = category;
        this.votes = votes;
    }
}
