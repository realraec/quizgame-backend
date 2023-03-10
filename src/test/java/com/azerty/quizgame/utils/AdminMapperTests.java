package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.AdminDTO;
import com.azerty.quizgame.model.entity.Admin;
import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminMapperTests {

    private final AdminMapper adminMapper = new AdminMapper();

    @Test
    public void shouldGoFromAdminToAdminDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;

        Admin admin = new Admin(id, username, password, lastname, firstname, email, role);
        AdminDTO adminDTO = adminMapper.toAdminDTO(admin);

        Assertions.assertEquals(null, adminDTO.getId());
        Assertions.assertEquals(username, adminDTO.getUsername());
        Assertions.assertEquals(password, adminDTO.getPassword());
        Assertions.assertEquals(lastname, adminDTO.getLastname());
        Assertions.assertEquals(firstname, adminDTO.getFirstname());
        Assertions.assertEquals(email, adminDTO.getEmail());
        Assertions.assertEquals(role, adminDTO.getRole());
    }

    @Test
    public void shouldGoFromAdminDTOToAdmin() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;

        AdminDTO adminDTO = new AdminDTO(id, username, password, lastname, firstname, email, role);
        Admin admin = adminMapper.toAdmin(adminDTO);

        Assertions.assertEquals(null, admin.getId());
        Assertions.assertEquals(username, admin.getUsername());
        Assertions.assertEquals(password, admin.getPassword());
        Assertions.assertEquals(lastname, admin.getLastname());
        Assertions.assertEquals(firstname, admin.getFirstname());
        Assertions.assertEquals(email, admin.getEmail());
        Assertions.assertEquals(role, admin.getRole());
    }

}
