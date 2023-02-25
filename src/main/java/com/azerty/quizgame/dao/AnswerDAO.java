package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerDAO extends CrudRepository<Answer, Long> {

    @Query("""
            SELECT a
            FROM Answer a
            WHERE a.question.quiz.id = (:id)
            """)
    List<Answer> findAllAnswersByQuizId(Long id);

    @Query("""
            SELECT a
            FROM Answer a
            WHERE a.question.id = (:id)
            """)
    List<Answer> findAllAnswersByQuestionId(Long id);

}
