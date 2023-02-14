package com.azerty.quizgame.service;

import com.azerty.quizgame.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAllQuestions() throws Exception;

    QuestionDTO getQuestionById(Long id) throws Exception;

    QuestionDTO saveQuestion(QuestionDTO question) throws Exception;

    boolean deleteQuestionById(Long id) throws Exception;

    QuestionDTO updateQuestionById(QuestionDTO question, Long id) throws Exception;

}
