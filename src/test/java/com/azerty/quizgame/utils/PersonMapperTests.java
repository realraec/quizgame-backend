package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonMapperTests {

    private final PersonMapper personMapper = new PersonMapper();

    @Test
    public void shouldGoFromPersonToPersonDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz());
        quizzes.add(new Quiz());
        //Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);

        Person person = new Person(id, username, password, lastname, firstname, email, company, role, null);
        person.setQuizzes(quizzes);
        PersonDTO personDTO = personMapper.toPersonDTO(person);

        Assertions.assertEquals(null, personDTO.getId());
        Assertions.assertEquals(username, personDTO.getUsername());
        Assertions.assertEquals(password, personDTO.getPassword());
        Assertions.assertEquals(lastname, personDTO.getLastname());
        Assertions.assertEquals(firstname, personDTO.getFirstname());
        Assertions.assertEquals(email, personDTO.getEmail());
        Assertions.assertEquals(company, personDTO.getCompany());
        Assertions.assertEquals(role, personDTO.getRole());
        Assertions.assertEquals(quizzes.size(), personDTO.getQuizzesIds().length);
    }

    @Test
    public void shouldGoFromPersonDTOToPerson() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz());
        quizzes.add(new Quiz());
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);

        PersonDTO personDTO = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);
        Person person = personMapper.toPerson(personDTO);

        Assertions.assertEquals(null, person.getId());
        Assertions.assertEquals(username, person.getUsername());
        Assertions.assertEquals(password, person.getPassword());
        Assertions.assertEquals(lastname, person.getLastname());
        Assertions.assertEquals(firstname, person.getFirstname());
        Assertions.assertEquals(email, person.getEmail());
        Assertions.assertEquals(company, person.getCompany());
        Assertions.assertEquals(role, person.getRole());
        Assertions.assertEquals(quizzes.size(), person.getQuizzes().size());
    }

}
