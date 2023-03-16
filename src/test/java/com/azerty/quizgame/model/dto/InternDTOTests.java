package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForInternDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;

        InternDTO internDTO = new InternDTO();
        internDTO.setId(id);
        internDTO.setUsername(username);
        internDTO.setPassword(password);
        internDTO.setLastname(lastname);
        internDTO.setFirstname(firstname);
        internDTO.setEmail(email);
        internDTO.setCompany(company);
        internDTO.setRole(role);

        Assertions.assertEquals(id, internDTO.getId());
        Assertions.assertEquals(username, internDTO.getUsername());
        Assertions.assertEquals(password, internDTO.getPassword());
        Assertions.assertEquals(lastname, internDTO.getLastname());
        Assertions.assertEquals(firstname, internDTO.getFirstname());
        Assertions.assertEquals(email, internDTO.getEmail());
        Assertions.assertEquals(company, internDTO.getCompany());
        Assertions.assertEquals(role, internDTO.getRole());
    }

}
