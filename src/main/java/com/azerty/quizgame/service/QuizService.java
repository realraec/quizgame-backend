package com.azerty.quizgame.service;

import com.azerty.quizgame.dto.QuizDTO;

import java.util.List;

public interface QuizService {

    List<QuizDTO> getAllQuizzes() throws Exception;

    QuizDTO getQuizById(Long id) throws Exception;

    QuizDTO saveQuiz(QuizDTO quiz) throws Exception;

    boolean deleteQuizById(Long id) throws Exception;

    QuizDTO updateQuizById(QuizDTO quiz, Long id) throws Exception;

}
