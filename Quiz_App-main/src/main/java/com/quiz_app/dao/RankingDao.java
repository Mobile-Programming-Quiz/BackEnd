package com.quiz_app.dao;

import com.quiz_app.model.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RankingDao extends JpaRepository<Ranking, Long> {

    // 특정 날짜의 전체 랭킹을 가져오는 메서드
    List<Ranking> findByDateOrderByScoreDesc(LocalDate date);

    // 특정 날짜와 학교의 학교 랭킹을 가져오는 메서드
    List<Ranking> findByDateAndSchoolNameOrderByScoreDesc(LocalDate date, String schoolName);

    // 상위 10명의 전체 랭킹을 가져오는 메서드
    List<Ranking> findTop10ByOrderByScoreDesc();

    // 상위 10명의 학교 랭킹을 가져오는 메서드
    List<Ranking> findTop10BySchoolNameOrderByScoreDesc(String schoolName);
}
