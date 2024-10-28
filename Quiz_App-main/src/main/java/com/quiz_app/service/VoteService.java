package com.quiz_app.service;

import com.quiz_app.dao.VoteDao;
import com.quiz_app.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteDao voteDao;

    // 모든 투표 항목 가져오기
    public List<Vote> getAllVotes() {
        return voteDao.findAll();
    }

    // 특정 카테고리에 대한 투표 추가
    @Transactional
    public void addVote(String category) {
        Vote vote = voteDao.findByCategory(category).orElse(new Vote(category, 0));
        vote.setVotes(vote.getVotes() + 1);
        voteDao.save(vote);
    }

    // 오늘의 가장 높은 투표를 받은 카테고리 가져오기
    public String getWinningCategory() {
        return voteDao.findTopByOrderByVotesDesc()
                .map(Vote::getCategory)
                .orElse("Default Category");
    }

    // 투표 초기화 (매일 자정에 호출하여 투표 수 초기화)
    @Transactional
    public void resetVotes() {
        List<Vote> votes = voteDao.findAll();
        for (Vote vote : votes) {
            vote.setVotes(0);
        }
        voteDao.saveAll(votes);
    }
}
