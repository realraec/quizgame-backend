package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends CrudRepository<Quiz, Long> {
}
