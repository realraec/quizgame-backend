package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonDTOTests {

    @Test
    public void shouldInstantiatePersonDTONoArgConstructor() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        Long[] quizzesIds = {2L, 3L};

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(id);
        personDTO.setUsername(username);
        personDTO.setPassword(password);
        personDTO.setLastname(lastname);
        personDTO.setFirstname(firstname);
        personDTO.setEmail(email);
        personDTO.setCompany(company);
        personDTO.setRole(role);
        personDTO.setQuizzesIds(quizzesIds);

        Assertions.assertEquals(id, personDTO.getId());
        Assertions.assertEquals(username, personDTO.getUsername());
        Assertions.assertEquals(password, personDTO.getPassword());
        Assertions.assertEquals(lastname, personDTO.getLastname());
        Assertions.assertEquals(firstname, personDTO.getFirstname());
        Assertions.assertEquals(email, personDTO.getEmail());
        Assertions.assertEquals(company, personDTO.getCompany());
        Assertions.assertEquals(role, personDTO.getRole());
        Assertions.assertEquals(quizzesIds, personDTO.getQuizzesIds());
    }

    @Test
    public void shouldInstantiatePersonDTOAllArgsConstructor() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        Long[] quizzesIds = {2L, 3L};

        PersonDTO personDTO = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        Assertions.assertEquals(id, personDTO.getId());
        Assertions.assertEquals(username, personDTO.getUsername());
        Assertions.assertEquals(password, personDTO.getPassword());
        Assertions.assertEquals(lastname, personDTO.getLastname());
        Assertions.assertEquals(firstname, personDTO.getFirstname());
        Assertions.assertEquals(email, personDTO.getEmail());
        Assertions.assertEquals(company, personDTO.getCompany());
        Assertions.assertEquals(role, personDTO.getRole());
        Assertions.assertEquals(quizzesIds, personDTO.getQuizzesIds());
    }

}
