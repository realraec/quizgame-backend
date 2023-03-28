package com.azerty.quizgame.service;

import java.util.List;

import com.azerty.quizgame.model.dto.PersonDTO;

public interface PersonService
{

	List<PersonDTO> getAllAdmins() throws Exception;

	List<PersonDTO> getAllInterns() throws Exception;

	PersonDTO getPersonById(Long id) throws Exception;

	PersonDTO getPersonByUsername(String username) throws Exception;

	PersonDTO savePerson(PersonDTO person) throws Exception;

	boolean deletePersonById(Long id) throws Exception;

	PersonDTO updatePersonById(PersonDTO person, Long id) throws Exception;

	List<PersonDTO> getAllPersonsAttributedToQuizByQuizId(Long quizId) throws Exception;

	List<PersonDTO> getAllPersonsNotAttributedToQuizByQuizId(Long quizId) throws Exception;

}
