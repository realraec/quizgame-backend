package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuizzes() throws Exception;

    Quiz getQuizById(Long id) throws Exception;

    Quiz saveQuiz(Quiz quiz) throws Exception;

    boolean deleteQuizById(Long id) throws Exception;

    Quiz updateQuizById(Quiz quiz, Long id) throws Exception;

}
