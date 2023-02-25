package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Progress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressDAO extends CrudRepository<Progress, Long> {

    @Query("""
            SELECT p
            FROM Progress p
            WHERE p.intern.id = (:id)
            """)
    List<Progress> findAllProgressesByInternId(Long id);

    @Query("""
            SELECT p
            FROM Progress p
            WHERE p.intern.id = (:internId)
            AND p.quiz.id = (:quizId)
            """)
    Progress findProgressByInternIdAndQuizId(Long internId, Long quizId);

}
