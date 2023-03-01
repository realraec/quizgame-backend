package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionDAO extends CrudRepository<Question, Long> {

    @Query("""
            SELECT p.quiz.questions
            FROM Progress p
            WHERE p.intern.id = (:internId)
            AND p.quiz.id = (:quizId)
            """)
    List<Question> findAllQuestionsByInternIdAndQuizId(Long internId, Long quizId);

    @Query("""
            SELECT q
            FROM Question q
            WHERE q.quiz.id = (:quizId)
            """)
    List<Question> findAllQuestionsByQuizId(Long quizId);

    @Query("""
            SELECT q
            FROM Question q
            WHERE q.quiz.id = (:quizId)
            AND q.id NOT IN (:questionsIds)
            """)
    List<Question> findAllQuestionsByQuizIdWithIdNotInArray(Long quizId, Long[] questionsIds);

    //@Query(value = "SELECT questions.* FROM questions JOIN quizzes ON questions.fk_quiz = quizzes.pk_quiz JOIN progresses ON progresses.fk_quiz = quizzes.pk_quiz LEFT JOIN records ON records.fk_question = questions.pk_question AND records.fk_progress = progresses.pk_progress WHERE records.pk_record IS NULL AND progresses.pk_progress = (:progressId) LIMIT 1", nativeQuery = true)
    @Query("""
            SELECT qn
            FROM Question qn
            JOIN Quiz qz ON qn.quiz.id = qz.id
            JOIN Progress p ON p.quiz.id = qz.id
            LEFT JOIN Record r ON r.question.id = qn.id AND r.progress.id = p.id
            WHERE r.id IS NULL
            AND p.id = (:progressId)
            """)
    List<Question> findAllQuestionsInQuizWithIdNotInProgressRecordsByProgressId(Long progressId);

    @Query(nativeQuery = true, value = """
            SELECT
            	t.pk_question, t.max_duration_in_seconds, t.wording,
            	answers.pk_answer, answers.is_correct, answers.wording
            FROM
                        
            (SELECT *
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
