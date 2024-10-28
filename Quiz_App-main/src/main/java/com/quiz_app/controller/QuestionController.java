package com.quiz_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quiz_app.model.Question;
import com.quiz_app.service.QuestionService;

import java.util.List;

// 질문과 관련된 HTTP 요청을 처리하는 컨트롤러 클래스
@RestController
@RequestMapping("question")  // "/question" 경로로 들어오는 요청을 처리
public class QuestionController {

    // QuestionService 클래스의 인스턴스를 주입받아 사용
    @Autowired
    QuestionService questionService;

    // 모든 질문을 가져오는 메서드
    // HTTP GET 요청을 "/question/allQuestions" 경로로 받아 모든 질문 리스트를 반환
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        // questionService의 getAllQuestions 메서드를 호출하여 모든 질문 리스트를 반환
        return questionService.getAllQuestions();
    }

    // 특정 카테고리에 속한 질문들을 가져오는 메서드
    // HTTP GET 요청을 "/question/category/{category}" 경로로 받아 해당 카테고리의 질문 리스트를 반환
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        // 경로 변수로 전달된 category를 사용해 questionService의 getQuestionsByCategory 메서드를 호출하여 질문 리스트를 가져옴
        return questionService.getQuestionsByCategory(category);
    }

    // 새로운 질문을 추가하는 메서드
    // HTTP POST 요청을 "/question/add" 경로로 받아 새로운 질문을 추가
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        // 요청 본문으로 전달된 question 객체를 사용해 questionService의 addQuestion 메서드를 호출하여 질문을 추가
        return questionService.addQuestion(question);
    }
}
