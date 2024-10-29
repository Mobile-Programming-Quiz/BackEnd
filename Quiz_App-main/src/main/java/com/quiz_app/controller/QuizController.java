package com.quiz_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quiz_app.model.Question;
import com.quiz_app.model.QuestionWrapper;
import com.quiz_app.model.Response;
import com.quiz_app.service.QuizService;

import java.util.List;

// 퀴즈와 관련된 HTTP 요청을 처리하는 컨트롤러 클래스
@RestController
@RequestMapping("quiz")  // "/quiz" 경로로 들어오는 요청을 처리
public class QuizController {

    // QuizService 클래스의 인스턴스를 주입받아 사용
    @Autowired
    QuizService quizService;

    // 퀴즈를 생성하는 메서드
    // HTTP POST 요청을 "/quiz/create" 경로로 받아 퀴즈를 생성
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        // category, numQ(질문 수), title을 받아서 quizService의 createQuiz 메서드를 호출
        return quizService.createQuiz(category, numQ, title);
    }

    // 특정 퀴즈의 질문들을 가져오는 메서드
    // HTTP GET 요청을 "/quiz/get/{id}" 경로로 받아 해당 ID의 퀴즈 질문을 반환
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        // 경로 변수로 전달된 id를 사용해 quizService의 getQuizQuestions 메서드를 호출하여 질문 리스트를 가져옴
        return quizService.getQuizQuestions(id);
    }

    // 퀴즈 제출 및 점수 계산 메서드
    // HTTP POST 요청을 "/quiz/submit/{id}" 경로로 받아 퀴즈 답안을 제출하고 점수를 계산
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        // 경로 변수 id와 요청 본문으로 받은 responses(답변 리스트)를 사용하여 quizService의 calculateResult 메서드를 호출하여 점수를 계산
        return quizService.calculateResult(id, responses);
    }
}
