package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAllAnswers() throws Exception;

    Answer getAnswerById(Long id) throws Exception;

    Answer saveAnswer(Answer answer) throws Exception;

    boolean deleteAnswerById(Long id) throws Exception;

    Answer updateAnswerById(Answer answer, Long id) throws Exception;

}
