package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.InternDTO;
import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternMapperTests {

    private final InternMapper internMapper = new InternMapper();

    @Test
    public void shouldGoFromInternToInternDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;

        Intern admin = new Intern(id, username, password, lastname, firstname, email, company, role);
        InternDTO internDTO = internMapper.toInternDTO(admin);

        Assertions.assertEquals(null, internDTO.getId());
        Assertions.assertEquals(username, internDTO.getUsername());
        Assertions.assertEquals(password, internDTO.getPassword());
        Assertions.assertEquals(lastname, internDTO.getLastname());
        Assertions.assertEquals(firstname, internDTO.getFirstname());
        Assertions.assertEquals(email, internDTO.getEmail());
        Assertions.assertEquals(company, internDTO.getCompany());
        Assertions.assertEquals(role, internDTO.getRole());
    }

    @Test
    public void shouldGoFromInternDTOToIntern() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;

        InternDTO internDTO = new InternDTO(id, username, password, lastname, firstname, email, company, role);
        Intern intern = internMapper.toIntern(internDTO);

        Assertions.assertEquals(null, intern.getId());
        Assertions.assertEquals(username, intern.getUsername());
        Assertions.assertEquals(password, intern.getPassword());
        Assertions.assertEquals(lastname, intern.getLastname());
        Assertions.assertEquals(firstname, intern.getFirstname());
        Assertions.assertEquals(email, intern.getEmail());
        Assertions.assertEquals(company, intern.getCompany());
        Assertions.assertEquals(role, intern.getRole());
    }

}
