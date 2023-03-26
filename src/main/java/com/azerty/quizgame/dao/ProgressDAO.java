package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Progress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressDAO extends CrudRepository<Progress, Long> {

    @Query("""
            SELECT p
            FROM Progress p
            WHERE p.person.id = (:id)
            """)
    List<Progress> findAllProgressesByPersonId(Long id);

    @Query("""
            SELECT p
            FROM Progress p
            WHERE p.person.id = (:personId)
            AND p.quiz.id = (:quizId)
            """)
    Optional<Progress> findProgressByPersonIdAndQuizId(Long personId, Long quizId);

}
