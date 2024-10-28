package com.quiz_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz_app.dao.QuestionDao;
import com.quiz_app.dao.QuizDao;
import com.quiz_app.model.Question;
import com.quiz_app.model.QuestionWrapper;
import com.quiz_app.model.Quiz;
import com.quiz_app.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao; // Quiz 엔티티와 상호작용하는 DAO
    @Autowired
    QuestionDao questionDao; // Question 엔티티와 상호작용하는 DAO

    /**
     * 새로운 퀴즈를 생성하는 메서드.
     *
     * @param category 퀴즈 카테고리
     * @param numQ 퀴즈 문제 수
     * @param title 퀴즈 제목
     * @return 생성 성공 여부 응답 (HTTP 상태 코드 201 반환)
     */
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // 지정된 카테고리에서 랜덤으로 지정한 수만큼의 질문을 가져옴
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        // 새로운 퀴즈 객체 생성 후 제목과 질문 목록을 설정
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz); // 퀴즈를 데이터베이스에 저장

        return new ResponseEntity<>("Success", HttpStatus.CREATED); // 생성 성공 응답
    }

    /**
     * 특정 퀴즈의 질문 목록을 가져오는 메서드.
     * 질문 내용 외의 민감한 정보는 제외하고 제공.
     *
     * @param id 퀴즈 ID
     * @return 사용자에게 전달할 퀴즈 질문 목록 응답
     */
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id); // 퀴즈 ID로 퀴즈 조회
        List<Question> questionsFromDB = quiz.get().getQuestions(); // 퀴즈의 질문 목록 가져오기
        List<QuestionWrapper> questionsForUser = new ArrayList<>(); // 사용자에게 전달할 질문 목록 초기화

        // 각 질문의 상세 정보를 래핑하여 사용자에게 전달할 목록에 추가
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK); // 사용자에게 질문 목록 응답
    }

    /**
     * 퀴즈 결과를 계산하는 메서드.
     * 사용자의 응답과 정답을 비교하여 점수를 계산.
     *
     * @param id 퀴즈 ID
     * @param responses 사용자의 응답 목록
     * @return 맞춘 문제 수 (점수) 응답
     */
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get(); // 퀴즈 ID로 퀴즈 조회
        List<Question> questions = quiz.getQuestions(); // 퀴즈의 질문 목록 가져오기
        int right = 0; // 정답 수 초기화
        int i = 0; // 인덱스 초기화

        // 사용자의 응답과 각 질문의 정답을 순서대로 비교하여 정답 수 계산
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++; // 정답일 경우 맞춘 수 증가
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK); // 최종 점수 응답
    }
}
