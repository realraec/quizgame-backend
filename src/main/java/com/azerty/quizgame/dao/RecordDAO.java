package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecordDAO extends CrudRepository<Record, Long> {

    @Query("""
            SELECT r
            FROM Record r
            WHERE r.progress.id = (:progressId)
            AND r.question.id = (:questionId)
            """)
    Optional<Record> findRecordByProgressIdAndQuestionId(Long progressId, Long questionId);

}
