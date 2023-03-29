package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonTests {

    @Test
    public void shouldInstantiatePersonNoArgConstructor() {
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

    @Test
    public void shouldInstantiatePersonAllArgsConstructor() {
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

        Person person = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);

        Assertions.assertEquals(null, person.getId());
        Assertions.assertEquals(username, person.getUsername());
        Assertions.assertEquals(password, person.getPassword());
        Assertions.assertEquals(lastname, person.getLastname());
        Assertions.assertEquals(firstname, person.getFirstname());
        Assertions.assertEquals(email, person.getEmail());
        Assertions.assertEquals(company, person.getCompany());
        Assertions.assertEquals(role, person.getRole());
        Assertions.assertEquals(quizzes, person.getQuizzes());
        Assertions.assertEquals("Person{" +
                "id=" + null +
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

    @Test
    public void shouldSeeTwoDifferentPersonEntitiesAsDifferent() {
        Long id1 = 1L;
        Long id2 = 2L;
        Person person1 = new Person();
        person1.setId(id1);
        Person person2 = new Person();
        person2.setId(id2);

        Assertions.assertNotEquals(person1, person2);
        Assertions.assertTrue(person1.hashCode() != person2.hashCode());
    }

    @Test
    public void shouldSeeTwoSimilarPersonEntitiesAsSimilar() {
        Long id = 1L;
        Person person1 = new Person();
        person1.setId(id);
        Person person2 = new Person();
        person2.setId(id);

        Assertions.assertEquals(person1, person2);
        Assertions.assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void shouldXXX() {
        Person person1 = new Person();
        person1.setRole(Role.ADMIN);

        Assertions.assertNotNull(person1.getAuthorities());
        Assertions.assertTrue(person1.isAccountNonExpired());
        Assertions.assertTrue(person1.isAccountNonLocked());
        Assertions.assertTrue(person1.isCredentialsNonExpired());
        Assertions.assertTrue(person1.isEnabled());
    }

}
