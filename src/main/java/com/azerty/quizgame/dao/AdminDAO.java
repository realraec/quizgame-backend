package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminDAO extends CrudRepository<Admin, Long> {
}
