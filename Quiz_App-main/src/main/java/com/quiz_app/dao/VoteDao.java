package com.quiz_app.dao;

import com.quiz_app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {

    // 카테고리로 투표 데이터를 찾는 메서드
    Optional<Vote> findByCategory(String category);

    // 투표 수가 가장 높은 카테고리를 찾는 메서드
    Optional<Vote> findTopByOrderByVotesDesc();
}
