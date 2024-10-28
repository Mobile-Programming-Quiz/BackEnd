package com.quiz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz_app.dao.QuestionDao;
import com.quiz_app.model.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao; // Question 엔티티와 상호작용하는 DAO

    /**
     * 모든 질문을 가져오는 메서드.
     *
     * @return 질문 목록을 포함한 응답 (성공 시 HTTP 상태 코드 200, 실패 시 400 반환)
     */
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK); // 모든 질문 반환
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // 실패 시 빈 목록 반환
    }

    /**
     * 특정 카테고리의 질문 목록을 가져오는 메서드.
     *
     * @param category 가져올 질문의 카테고리
     * @return 지정된 카테고리의 질문 목록 (성공 시 HTTP 상태 코드 200, 실패 시 400 반환)
     */
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK); // 지정된 카테고리의 질문 반환
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST); // 실패 시 빈 목록 반환
    }

    /**
     * 새로운 질문을 추가하는 메서드.
     *
     * @param question 추가할 질문 객체
     * @return 추가 성공 여부 응답 (HTTP 상태 코드 201 반환)
     */
    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question); // 질문을 데이터베이스에 저장
        return new ResponseEntity<>("success", HttpStatus.CREATED); // 성공 메시지와 상태 반환
    }
}
