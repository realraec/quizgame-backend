package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Journey;
import org.springframework.data.repository.CrudRepository;

public interface JourneyDAO extends CrudRepository<Journey, Long>  {
}
