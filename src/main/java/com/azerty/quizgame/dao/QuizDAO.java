package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDAO extends CrudRepository<Quiz, Long> {

    @Query(nativeQuery = true, value = """
            SELECT quizzes.*
            FROM quizzes
            JOIN quizzes_persons
            ON quizzes.pk_quiz = quizzes_persons.pk_quiz
            WHERE quizzes_persons.pk_person = (:id)
            """)
    List<Quiz> findAllQuizzesByPersonId(Long id);

}
