package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerDAO extends CrudRepository<Answer, Long> {

    @Query("""
            SELECT a
            FROM Answer a
            WHERE a.question.id = (:questionId)
            """)
    List<Answer> findAllAnswersByQuestionId(Long questionId);

    @Query("""
            SELECT a.id
            FROM Answer a
            WHERE a.question.id = (:questionId)
            AND a.isCorrect = true
            """)
    List<Answer> findCorrectAnswersIdsByQuestionId(Long questionId);

}
