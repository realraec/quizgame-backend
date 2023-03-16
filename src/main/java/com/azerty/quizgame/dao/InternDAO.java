package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.entity.Intern;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternDAO extends CrudRepository<Intern, Long> {
}
