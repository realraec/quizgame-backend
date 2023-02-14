package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Intern;

import java.util.List;

public interface InternService {

    List<Intern> getAllInterns() throws Exception;

    Intern getInternById(Long id) throws Exception;

    Intern saveIntern(Intern intern) throws Exception;

    boolean deleteInternById(Long id) throws Exception;

    Intern updateInternById(Intern intern, Long id) throws Exception;

}
