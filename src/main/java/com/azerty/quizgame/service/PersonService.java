package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getAllAdmins() throws Exception;

    List<PersonDTO> getAllInterns() throws Exception;

    PersonDTO getPersonById(Long id) throws Exception;

    PersonDTO savePerson(PersonDTO person) throws Exception;

    boolean deletePersonById(Long id) throws Exception;

    PersonDTO updatePersonById(PersonDTO person, Long id) throws Exception;

}
