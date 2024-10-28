package com.quiz_app.service;

import com.quiz_app.dao.RankingDao;
import com.quiz_app.model.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingDao rankingDao;

    // 오늘의 전체 랭킹을 가져오는 메서드
    public List<Ranking> getOverallDailyRanking() {
        LocalDate today = LocalDate.now();
        return rankingDao.findByDateOrderByScoreDesc(today);
    }

    // 오늘의 학교별 랭킹을 가져오는 메서드
    public List<Ranking> getSchoolDailyRanking(String schoolName) {
        LocalDate today = LocalDate.now();
        return rankingDao.findByDateAndSchoolNameOrderByScoreDesc(today, schoolName);
    }

    // 상위 10명의 전체 랭킹을 가져오는 메서드
    public List<Ranking> getOverallTopRanking() {
        return rankingDao.findTop10ByOrderByScoreDesc();
    }

    // 상위 10명의 학교별 랭킹을 가져오는 메서드
    public List<Ranking> getSchoolTopRanking(String schoolName) {
        return rankingDao.findTop10BySchoolNameOrderByScoreDesc(schoolName);
    }

    // 새로운 랭킹을 저장하는 메서드
    public void saveRanking(String username, int score, String schoolName) {
        Ranking ranking = new Ranking(username, score, LocalDate.now(), schoolName);
        rankingDao.save(ranking);
    }
}
