package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Intern;
import org.springframework.data.repository.CrudRepository;

public interface InternDAO extends CrudRepository<Intern, Long> {
}
