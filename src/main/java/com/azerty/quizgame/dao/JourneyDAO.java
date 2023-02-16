package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Journey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JourneyDAO extends CrudRepository<Journey, Long> {

    @Query("SELECT j FROM Journey j WHERE j.intern.id = (:id)")
    List<Journey> findAllJourneysByInternId(Long id);

    @Query("SELECT j FROM Journey j WHERE j.intern.id = (:internId) AND j.quiz.id = (:quizId)")
    Journey findJourneyByInternIdAndQuizId(Long internId, Long quizId);

}
