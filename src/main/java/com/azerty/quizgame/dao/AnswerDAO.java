package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerDAO extends CrudRepository<Answer, Long> {
}
