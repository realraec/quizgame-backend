package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.utils.CountsMapper;
import com.azerty.quizgame.utils.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonDAO personDAO;
    private final PersonMapper personMapper = new PersonMapper();
    private final CountsMapper countsMapper = new CountsMapper();



    @Autowired
    public PersonServiceImplementation(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public List<PersonDTO> getAllAdmins() {
        Iterator<Person> adminIterator = personDAO.findAllPersonsWithRoleAdmin().iterator();
        List<PersonDTO> admins = new ArrayList<>();
        while (adminIterator.hasNext()) {
            admins.add(personMapper.toPersonDTO(adminIterator.next()));
        }

        if (!admins.isEmpty()) {
            return admins;
        } else {
            return null;
        }
    }

    @Override
    public List<PersonDTO> getAllInterns() {
        Iterator<Person> internIterator = personDAO.findAllPersonsWithRoleIntern().iterator();
        List<PersonDTO> interns = new ArrayList<>();
        while (internIterator.hasNext()) {
            interns.add(personMapper.toPersonDTO(internIterator.next()));
        }

        if (!interns.isEmpty()) {
            return interns;
        } else {
            return null;
        }
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Optional<Person> person = personDAO.findPersonById(id);
        if (person.isPresent()) {
            return personMapper.toPersonDTO(person.get());
        } else {
            return null;
        }
    }

    @Override
    public boolean deletePersonById(Long id) {
        Optional<Person> checkPerson = personDAO.findById(id);
        if (checkPerson.isPresent()) {
            personDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PersonDTO savePerson(PersonDTO person) {
        return personMapper.toPersonDTO(personDAO.save(personMapper.toPerson(person)));
    }

    @Override
    public PersonDTO updatePersonById(PersonDTO person, Long id) {
        Optional<Person> checkPerson = personDAO.findById(id);
        if (checkPerson.isPresent()) {
            Person personAsEntity = personMapper.toPerson(person);
            personAsEntity.setId(id);
            return personMapper.toPersonDTO(personDAO.save(personAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public CountsDTO getInternCountAndQuizCount() {
        Long[] counts = personDAO.findPersonWithRoleInternCountAndQuizCount();
        return countsMapper.toCountsDTO(counts);
    }


}
