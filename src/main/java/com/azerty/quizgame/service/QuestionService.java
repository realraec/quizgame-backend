package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAllQuestions() throws Exception;

    QuestionDTO getQuestionById(Long id) throws Exception;

    QuestionDTO saveQuestion(QuestionDTO question) throws Exception;

    boolean deleteQuestionById(Long id) throws Exception;

    QuestionDTO updateQuestionById(QuestionDTO question, Long id) throws Exception;

    List<QuestionDTO> getAllQuestionsByQuizId(Long quizId) throws Exception;

    List<QuestionDTO> getAllQuestionsByQuizIdWithIdNotInArray(Long quizId, Long[] questionsIds) throws Exception;

    List<QuestionDTO> getAllQuestionsByInternIdAndQuizId(Long internId, Long quizId) throws Exception;

    QuestionDTO getSingleQuestionInQuizWithIdNotInProgressRecordsByProgressId(Long progressId) throws Exception;


}
