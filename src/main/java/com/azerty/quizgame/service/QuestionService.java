package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions() throws Exception;

    Question getQuestionById(Long id) throws Exception;

    Question saveQuestion(Question question) throws Exception;

    boolean deleteQuestionById(Long id) throws Exception;

    Question updateQuestionById(Question question, Long id) throws Exception;

}
