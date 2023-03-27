package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.utils.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImplementation implements PersonService {

    private final PersonDAO personDAO;
    private final QuizDAO quizDAO;
    private final PersonMapper personMapper = new PersonMapper();


    @Autowired
    public PersonServiceImplementation(PersonDAO personDAO,
                                       QuizDAO quizDAO) {
        this.personDAO = personDAO;
        this.quizDAO = quizDAO;
    }


    @Override
    public List<PersonDTO> getAllAdmins() {
        try {
            Iterator<Person> adminIterator = personDAO.findAllPersonsWithRoleAdmin().iterator();
            List<PersonDTO> admins = new ArrayList<>();
            while (adminIterator.hasNext()) {
                admins.add(personMapper.toPersonDTO(adminIterator.next()));
            }
            return admins;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<PersonDTO> getAllInterns() {
        try {
            Iterator<Person> internIterator = personDAO.findAllPersonsWithRoleIntern().iterator();
            List<PersonDTO> interns = new ArrayList<>();
            while (internIterator.hasNext()) {
                interns.add(personMapper.toPersonDTO(internIterator.next()));
            }
            return interns;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Optional<Person> person = personDAO.findById(id);
        return person.map(personMapper::toPersonDTO).orElse(null);
    }

    @Override
    public PersonDTO savePerson(PersonDTO person) {
        Long[] quizzesIds = person.getQuizzesIds();
        if (quizzesIds != null && quizzesIds.length > 0) {
            for (int i = 0; i < quizzesIds.length; i++) {
                Optional<Quiz> checkRecord = quizDAO.findById(quizzesIds[i]);
                if (checkRecord.isEmpty()) {
                    return null;
                }
            }

        } else {
            person.setQuizzesIds(new Long[]{});
        }
        Person personAsEntity = personMapper.toPerson(person);
        personAsEntity.setId(person.getId());
        return personMapper.toPersonDTO(personDAO.save(personAsEntity));
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
    public PersonDTO updatePersonById(PersonDTO person, Long id) {
        Optional<Person> checkPerson = personDAO.findById(id);
        if (checkPerson.isPresent()) {
            Person personAsEntity = personMapper.toPerson(person);
            personAsEntity.setId(id);
            return savePerson(personMapper.toPersonDTO(personAsEntity));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<PersonDTO> getAllPersonsAttributedToQuizByQuizId(Long quizId) {
        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        if (checkQuiz.isPresent()) {
            Iterator<Person> personIterator = personDAO.findAllPersonsAttributedToQuizByQuizId(quizId).iterator();
            List<PersonDTO> persons = new ArrayList<>();
            while (personIterator.hasNext()) {
                persons.add(personMapper.toPersonDTO(personIterator.next()));
            }
            return persons;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<PersonDTO> getAllPersonsNotAttributedToQuizByQuizId(Long quizId) {
        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        if (checkQuiz.isPresent()) {
            Iterator<Person> personIterator = personDAO.findAllPersonsNotAttributedToQuizByQuizId(quizId).iterator();
            List<PersonDTO> persons = new ArrayList<>();
            while (personIterator.hasNext()) {
                persons.add(personMapper.toPersonDTO(personIterator.next()));
            }
            return persons;
        } else {
            return null;
        }
    }

}
