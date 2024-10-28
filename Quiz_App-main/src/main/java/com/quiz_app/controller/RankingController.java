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

    // 오늘의 전체 랭킹을 가져오는 엔드포인트
    @GetMapping("/daily/overall")
    public ResponseEntity<List<Ranking>> getOverallDailyRanking() {
        List<Ranking> dailyRanking = rankingService.getOverallDailyRanking();
        return ResponseEntity.ok(dailyRanking);
    }

    // 오늘의 학교 랭킹을 가져오는 엔드포인트
    @GetMapping("/daily/school")
    public ResponseEntity<List<Ranking>> getSchoolDailyRanking(@RequestParam String schoolName) {
        List<Ranking> schoolRanking = rankingService.getSchoolDailyRanking(schoolName);
        return ResponseEntity.ok(schoolRanking);
    }

    // 전체 상위 10명의 랭킹을 가져오는 엔드포인트
    @GetMapping("/top/overall")
    public ResponseEntity<List<Ranking>> getOverallTopRanking() {
        List<Ranking> topRanking = rankingService.getOverallTopRanking();
        return ResponseEntity.ok(topRanking);
    }

    // 학교별 상위 10명의 랭킹을 가져오는 엔드포인트
    @GetMapping("/top/school")
    public ResponseEntity<List<Ranking>> getSchoolTopRanking(@RequestParam String schoolName) {
        List<Ranking> topSchoolRanking = rankingService.getSchoolTopRanking(schoolName);
        return ResponseEntity.ok(topSchoolRanking);
    }

    // 랭킹을 저장하는 엔드포인트 (테스트용)
    @PostMapping("/add")
    public ResponseEntity<String> addRanking(@RequestParam String username, @RequestParam int score, @RequestParam(required = false) String schoolName) {
        rankingService.saveRanking(username, score, schoolName);
        return ResponseEntity.ok("Ranking added successfully");
    }
}
