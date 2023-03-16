package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonDAO extends CrudRepository<Person, Long> {

    @Query("""
            SELECT p
            FROM Person p
            WHERE p.role = 'ADMIN'
            """)
    List<Person> findAllPersonsWithRoleAdmin();

    @Query("""
            SELECT p
            FROM Person p
            WHERE p.role = 'INTERN'
            """)
    List<Person> findAllPersonsWithRoleIntern();

/*    @Query("""
            SELECT p
            FROM Person p
            WHERE p.id = (:id)
            """)*/
    Optional<Person> findPersonById(Long id);

    @Query(nativeQuery = true, value = """
            SELECT DISTINCT COUNT(*) OVER() FROM persons WHERE persons.role = 'INTERN'
            UNION ALL
            SELECT DISTINCT COUNT(*) OVER() FROM quizzes;
            """)
    Long[] findPersonWithRoleInternCountAndQuizCount();


}
