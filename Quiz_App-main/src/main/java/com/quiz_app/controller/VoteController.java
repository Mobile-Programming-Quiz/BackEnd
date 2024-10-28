//사용자가 투표할 수 있는 엔드포인트를 제공합니다.
// 예를 들어, /vote/tomorrowTheme 같은 경로를 사용하여 투표를 관리
package com.quiz_app.controller;

import com.quiz_app.model.Vote;
import com.quiz_app.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // 모든 투표 항목과 현재 투표 수를 가져오는 엔드포인트
    @GetMapping("/all")
    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> votes = voteService.getAllVotes();
        return ResponseEntity.ok(votes);
    }

    // 사용자가 특정 카테고리에 투표할 수 있는 엔드포인트
    @PostMapping("/add")
    public ResponseEntity<String> addVote(@RequestParam String category) {
        voteService.addVote(category);
        return ResponseEntity.ok("Vote added successfully for category: " + category);
    }

    // 현재 가장 높은 투표를 받은 카테고리를 가져오는 엔드포인트
    @GetMapping("/winningCategory")
    public ResponseEntity<String> getWinningCategory() {
        String winningCategory = voteService.getWinningCategory();
        return ResponseEntity.ok("The winning category for tomorrow's quiz is: " + winningCategory);
    }

    // (관리자용) 모든 투표를 초기화하는 엔드포인트 (일반적으로 매일 자정에 호출)
    @PostMapping("/reset")
    public ResponseEntity<String> resetVotes() {
        voteService.resetVotes();
        return ResponseEntity.ok("All votes have been reset.");
    }
}
