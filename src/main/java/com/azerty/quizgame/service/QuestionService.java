package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAllQuestions() throws Exception;

    QuestionDTO getQuestionById(Long id) throws Exception;

    QuestionDTO saveQuestion(QuestionDTO question) throws Exception;

    boolean deleteQuestionById(Long id) throws Exception;

    QuestionDTO updateQuestionById(QuestionDTO question, Long id) throws Exception;

    List<QuestionDTO> getAllQuestionsByQuizId(Long quizId) throws Exception;

    QuestionDTO getOneQuestionInQuizWithIdNotInProgressRecordsByProgressId(Long progressId) throws Exception;

    QuestionInQuizDTO getOneQuestionAndAllItsAnswersInQuizWithIdNotInProgressRecordsByProgressId(Long progressId) throws Exception;

}
