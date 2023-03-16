package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {

    List<AnswerDTO> getAllAnswers() throws Exception;

    AnswerDTO getAnswerById(Long id) throws Exception;

    AnswerDTO saveAnswer(AnswerDTO answer) throws Exception;

    boolean deleteAnswerById(Long id) throws Exception;

    AnswerDTO updateAnswerById(AnswerDTO answer, Long id) throws Exception;

    List<AnswerDTO> getAllAnswersByQuestionId(Long id) throws Exception;

}
