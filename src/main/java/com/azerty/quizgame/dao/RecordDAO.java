package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordDAO extends CrudRepository<Record, Long> {

//    @Query("SELECT r FROM Record r WHERE r.question.quiz.id = (:quizId) AND WHERE (:internId) NOT IN Quiz.questions")
//    List<Record> findAllRecordsByInternIdQuizId(Long internId, Long quizId);

}
