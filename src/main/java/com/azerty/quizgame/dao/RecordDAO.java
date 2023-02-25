package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordDAO extends CrudRepository<Record, Long> {
}
