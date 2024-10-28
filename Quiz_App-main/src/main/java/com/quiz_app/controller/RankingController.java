// /ranking/daily 및 /ranking/weekly 등의 엔드포인트로 랭킹 데이터를 가져오는 API를 작성합니다.
//RankingController는 랭킹 데이터를 조회하고 추가할 수 있는 API 엔드포인트를 제공합니다.
// /daily와 /weekly 엔드포인트를 통해 각각 일일 및 주간 랭킹을 가져옵니다.


package com.quiz_app.controller;

import com.quiz_app.model.Ranking;
import com.quiz_app.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    // 오늘의 랭킹을 가져오는 엔드포인트
    @GetMapping("/daily")
    public ResponseEntity<List<Ranking>> getDailyRanking() {
        List<Ranking> dailyRanking = rankingService.getDailyRanking();
        return ResponseEntity.ok(dailyRanking);
    }

    // 주간 랭킹을 가져오는 엔드포인트
    @GetMapping("/weekly")
    public ResponseEntity<List<Ranking>> getWeeklyRanking() {
        List<Ranking> weeklyRanking = rankingService.getWeeklyRanking();
        return ResponseEntity.ok(weeklyRanking);
    }

    // 랭킹을 저장하는 엔드포인트 (테스트용)
    @PostMapping("/add")
    public ResponseEntity<String> addRanking(@RequestParam String username, @RequestParam int score, @RequestParam Ranking.RankingType rankingType) {
        rankingService.saveRanking(username, score, rankingType);
        return ResponseEntity.ok("Ranking added successfully");
    }
}
