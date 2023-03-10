package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternTests {

    @Test
    public void shouldGetAndSetAllAttributesForIntern() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;

        Intern intern = new Intern();
        intern.setId(id);
        intern.setUsername(username);
        intern.setPassword(password);
        intern.setLastname(lastname);
        intern.setFirstname(firstname);
        intern.setEmail(email);
        intern.setCompany(company);
        intern.setRole(role);

        Assertions.assertEquals(id, intern.getId());
        Assertions.assertEquals(username, intern.getUsername());
        Assertions.assertEquals(password, intern.getPassword());
        Assertions.assertEquals(lastname, intern.getLastname());
        Assertions.assertEquals(firstname, intern.getFirstname());
        Assertions.assertEquals(email, intern.getEmail());
        Assertions.assertEquals(company, intern.getCompany());
        Assertions.assertEquals(role, intern.getRole());
        Assertions.assertEquals("Intern{" +
                "company='" + company + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}', intern.toString());
    }

}
