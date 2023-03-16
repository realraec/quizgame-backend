package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonTests {

    @Test
    public void shouldGetAndSetAllAttributesForPerson() {
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
        quizzes.add((new Quiz()));

        Person person = new Person();
        person.setId(id);
        person.setUsername(username);
        person.setPassword(password);
        person.setLastname(lastname);
        person.setFirstname(firstname);
        person.setEmail(email);
        person.setCompany(company);
        person.setRole(role);
        person.setQuizzes(quizzes);

        Assertions.assertEquals(id, person.getId());
        Assertions.assertEquals(username, person.getUsername());
        Assertions.assertEquals(password, person.getPassword());
        Assertions.assertEquals(lastname, person.getLastname());
        Assertions.assertEquals(firstname, person.getFirstname());
        Assertions.assertEquals(email, person.getEmail());
        Assertions.assertEquals(company, person.getCompany());
        Assertions.assertEquals(role, person.getRole());
        Assertions.assertEquals(quizzes, person.getQuizzes());
        Assertions.assertEquals("Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", company='" + company + '\'' +
                ", quizzes=" + quizzes +
                '}', person.toString());
    }

}
