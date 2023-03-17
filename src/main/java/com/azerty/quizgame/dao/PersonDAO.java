package com.azerty.quizgame.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.azerty.quizgame.model.entity.Person;

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

    @Query(nativeQuery = true, value = """
            SELECT DISTINCT persons.pk_person, persons.company, persons.email, persons.firstname,
                            persons.lastname, persons.password, persons.role, persons.username
            FROM persons
            JOIN quizzes_persons qp ON persons.pk_person = qp.pk_person
            WHERE qp.pk_quiz = (:quizId)
            """)
    List<Person> findAllPersonsAttributedToQuizByQuizId(Long quizId);

    @Query(nativeQuery = true, value = """
            SELECT DISTINCT persons.pk_person, persons.company, persons.email, persons.firstname,
                            persons.lastname, persons.password, persons.role, persons.username
            FROM persons
            LEFT JOIN quizzes_persons qp ON persons.pk_person = qp.pk_person AND qp.pk_quiz = (:quizId)
            WHERE qp.pk_quiz IS NULL
            AND persons.role = 'INTERN';
            """)
    List<Person> findAllPersonsNotAttributedToQuizByQuizId(Long quizId);

    /*
	 * @Query(""" SELECT p FROM Person p WHERE p.id = (:id) """)
	 */
	Optional<Person> findPersonById(Long id);

	@Query(nativeQuery = true, value = """
			SELECT DISTINCT COUNT(*) OVER() FROM persons WHERE persons.role = 'INTERN'
			UNION ALL
			SELECT DISTINCT COUNT(*) OVER() FROM quizzes;
			""")
	Long[] findPersonWithRoleInternCountAndQuizCount();

	@Query(nativeQuery = true, value = """
			SELECT p
			FROM Person p
			WHERE p.username = (:username)
			""")
	Optional<Person> findAnyPersonByUsername(String username);

}
