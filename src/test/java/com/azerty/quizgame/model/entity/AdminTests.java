package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminTests {

    @Test
    public void shouldGetAndSetAllAttributesForAdmin() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;

        Admin admin = new Admin();
        admin.setId(id);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setLastname(lastname);
        admin.setFirstname(firstname);
        admin.setEmail(email);
        admin.setRole(role);

        Assertions.assertEquals(id, admin.getId());
        Assertions.assertEquals(username, admin.getUsername());
        Assertions.assertEquals(password, admin.getPassword());
        Assertions.assertEquals(lastname, admin.getLastname());
        Assertions.assertEquals(firstname, admin.getFirstname());
        Assertions.assertEquals(email, admin.getEmail());
        Assertions.assertEquals(role, admin.getRole());
        Assertions.assertEquals("Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}', admin.toString());
    }

}
