//점수 계산 및 랭킹 업데이트 로직을 구현합니다.
// 예를 들어, 특정 퀴즈가 완료되면 사용자의 점수를 계산하고 Ranking 테이블에 추가합니다.
//RankingService는 비즈니스 로직을 담당합니다.
// getDailyRanking과 getWeeklyRanking 메서드는 각각 일일 및 주간 랭킹을 가져오는 역할을 하며,
// saveRanking 메서드는 새로운 랭킹 데이터를 저장합니다.
package com.quiz_app.service;

import com.quiz_app.dao.RankingDao;
import com.quiz_app.model.Ranking;
import com.quiz_app.model.Ranking.RankingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingDao rankingDao;

    // 오늘의 랭킹을 가져오는 메서드
    public List<Ranking> getDailyRanking() {
        LocalDate today = LocalDate.now();
        return rankingDao.findByDateAndRankingTypeOrderByScoreDesc(today, RankingType.DAILY);
    }

    // 주간 랭킹을 가져오는 메서드
    public List<Ranking> getWeeklyRanking() {
        return rankingDao.findTop10ByRankingTypeOrderByScoreDesc(RankingType.WEEKLY);
    }

    // 새로운 랭킹을 저장하는 메서드
    public void saveRanking(String username, int score, RankingType rankingType) {
        Ranking ranking = new Ranking(username, score, LocalDate.now(), rankingType);
        rankingDao.save(ranking);
    }
}
