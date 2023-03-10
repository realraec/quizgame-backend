package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends CrudRepository<Question, Long> {

    List<Question> findAllByQuizId(Long quizId);

    @Query(nativeQuery = true, value = """
            SELECT
            	t.pk_question, t.wording, t.max_duration_in_seconds,
            	answers.pk_answer, answers.is_correct, answers.wording,
            	t.number_of_questions_left
            FROM
                        
            (SELECT *, COUNT(questions.pk_question) OVER() AS number_of_questions_left
             FROM questions
             JOIN quizzes ON questions.fk_quiz = quizzes.pk_quiz
             JOIN progresses ON progresses.fk_quiz = quizzes.pk_quiz
             LEFT JOIN records ON records.fk_question = questions.pk_question AND records.fk_progress = progresses.pk_progress
             WHERE records.pk_record IS NULL
             AND progresses.pk_progress = (:progressId)
             LIMIT 1) t
                        
            JOIN answers ON answers.fk_question = t.pk_question
            """)
    List<Object[]> findOneQuestionInQuizTogetherWithAllItsAnswersWithIdNotInProgressRecordsByProgressId(Long progressId);

}
