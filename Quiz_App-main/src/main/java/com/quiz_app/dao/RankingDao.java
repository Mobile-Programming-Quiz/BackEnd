//RankingDao는 Ranking 데이터를 관리하기 위해 JPA 리포지토리를 확장합니다.
//특정 날짜와 타입으로 일일 랭킹을 가져오고, 주간 랭킹은 상위 10명의 점수로 가져오도록 메서드를 정의했습니다.


package com.quiz_app.dao;

import com.quiz_app.model.Ranking;
import com.quiz_app.model.Ranking.RankingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//데이터베이스에서 랭킹 데이터를 관리
@Repository
public interface RankingDao extends JpaRepository<Ranking, Long> {

    // 특정 날짜와 랭킹 타입에 따라 랭킹 리스트를 가져오는 메서드
    List<Ranking> findByDateAndRankingTypeOrderByScoreDesc(LocalDate date, RankingType rankingType);

    // 최근 일주일의 주간 랭킹 리스트를 가져오는 메서드
    List<Ranking> findTop10ByRankingTypeOrderByScoreDesc(RankingType rankingType);
}
