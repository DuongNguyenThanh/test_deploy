package vn.edu.clevai.quiztest.filter.mapper;

import org.springframework.stereotype.Component;
import vn.edu.clevai.quiztest.entity.Quiz;
import vn.edu.clevai.quiztest.payload.request.QuizRequest;

@Component
public class QuizMapper {

    public QuizRequest toRequest(Quiz quiz) {
        QuizRequest request = new QuizRequest();
        request.setId(quiz.getId());
        request.setExplanation(quiz.getExplanation());
        request.setExpAmount(quiz.getExpAmount());
        request.setQuizQuestion(quiz.getQuizQuestion());
        request.setQuizTypeId(quiz.getQuizType().getId());
        request.setLearningObjectId(quiz.getLearningObject().getId());
        return request;
    }

    public Quiz toEntity(QuizRequest quizRequest) {
        Quiz quiz = new Quiz();
        quiz.setQuizQuestion(quizRequest.getQuizQuestion());
        quiz.setExplanation(quizRequest.getExplanation());
        quiz.setExpAmount(quizRequest.getExpAmount());
        return quiz;
    }

    public Quiz toEntity(QuizRequest request, Quiz entity) {
        entity.setExplanation(request.getExplanation());
        entity.setExpAmount(request.getExpAmount());
        entity.setQuizQuestion(request.getQuizQuestion());
        return entity;
    }
}
