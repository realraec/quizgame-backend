package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionDAO extends CrudRepository<Question, Long> {
}
