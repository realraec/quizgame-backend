package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForAdminDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(id);
        adminDTO.setUsername(username);
        adminDTO.setPassword(password);
        adminDTO.setEmail(email);
        adminDTO.setLastname(lastname);
        adminDTO.setFirstname(firstname);
        adminDTO.setRole(role);

        Assertions.assertEquals(id, adminDTO.getId());
        Assertions.assertEquals(username, adminDTO.getUsername());
        Assertions.assertEquals(password, adminDTO.getPassword());
        Assertions.assertEquals(email, adminDTO.getEmail());
        Assertions.assertEquals(lastname, adminDTO.getLastname());
        Assertions.assertEquals(firstname, adminDTO.getFirstname());
        Assertions.assertEquals(role, adminDTO.getRole());
    }

}
