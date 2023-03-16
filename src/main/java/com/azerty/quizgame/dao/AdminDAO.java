package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends CrudRepository<Admin, Long> {

    @Query(nativeQuery = true, value = """
            SELECT DISTINCT COUNT(*) OVER() FROM interns
            UNION ALL
            SELECT DISTINCT COUNT(*) OVER() FROM quizzes;
            """)
    Long[] findInternCountAndQuizCount();

}
