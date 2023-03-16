package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PersonServiceTests {

    @InjectMocks
    private PersonServiceImplementation personService;

    @Mock
    private PersonDAO personDAO;

    @Test
    public void shouldGetAllAdmins() {
        // Given
        Long id1 = 1L;
        String username1 = "user1";
        String password1 = "P@ssword1";
        String lastname1 = "Tyson";
        String firstname1 = "Mike";
        String email1 = "mike.tyson@gmail.com";
        String company1 = null;
        Role role1 = Role.ADMIN;
        List<Quiz> quizzes1 = new ArrayList<>();
        Person admin1 = new Person(id1, username1, password1, lastname1, firstname1, email1, company1, role1, quizzes1);
        Long id2 = 1L;
        String username2 = "user2";
        String password2 = "P@ssword2";
        String lastname2 = "Tyyysooon";
        String firstname2 = "Miiikeee";
        String email2 = "miiikeee.tyyyson@gmail.com";
        String company2 = null;
        Role role2 = Role.ADMIN;
        List<Quiz> quizzes2 = new ArrayList<>();
        Person admin2 = new Person(id2, username2, password2, lastname2, firstname2, email2, company2, role2, quizzes2);

        List<Person> adminsToReturn = new ArrayList<>();
        adminsToReturn.add(admin1);
        adminsToReturn.add(admin2);
        Mockito.when(personDAO.findAllPersonsWithRoleAdmin()).thenReturn(adminsToReturn);

        // When
        List<PersonDTO> admins = personService.getAllAdmins();

        //Then
        assertEquals(adminsToReturn.size(), admins.size());
    }

    @Test
    public void shouldNotGetAllAdmins() {
        // Given
        Mockito.when(personDAO.findAllPersonsWithRoleAdmin()).thenReturn(null);

        // When
        List<PersonDTO> admins = personService.getAllAdmins();

        //Then
        assertEquals(null, admins);
    }

    @Test
    public void shouldGetAllInterns() {
        // Given
        Long id1 = 1L;
        String username1 = "user1";
        String password1 = "P@ssword1";
        String lastname1 = "Tyson";
        String firstname1 = "Mike";
        String email1 = "mike.tyson@gmail.com";
        String company1 = null;
        Role role1 = Role.INTERN;
        List<Quiz> quizzes1 = new ArrayList<>();
        quizzes1.add(new Quiz());
        quizzes1.add(new Quiz());
        Person intern1 = new Person(id1, username1, password1, lastname1, firstname1, email1, company1, role1, quizzes1);
        Long id2 = 2L;
        String username2 = "user2";
        String password2 = "P@ssword2";
        String lastname2 = "Tyyysooon";
        String firstname2 = "Miiikeee";
        String email2 = "miiikeee.tyyyson@gmail.com";
        String company2 = null;
        Role role2 = Role.INTERN;
        List<Quiz> quizzes2 = new ArrayList<>();
        quizzes2.add(new Quiz());
        quizzes2.add(new Quiz());
        Person intern2 = new Person(id2, username2, password2, lastname2, firstname2, email2, company2, role2, quizzes2);
        List<Person> internsToReturn = new ArrayList<>();
        internsToReturn.add(intern1);
        internsToReturn.add(intern2);
        Mockito.when(personDAO.findAllPersonsWithRoleIntern()).thenReturn(internsToReturn);

        // When
        List<PersonDTO> interns = personService.getAllInterns();

        //Then
        assertEquals(internsToReturn.size(), interns.size());
    }

    @Test
    public void shouldNotGetAllInterns() {
        // Given
        Mockito.when(personDAO.findAllPersonsWithRoleIntern()).thenReturn(null);

        // When
        List<PersonDTO> interns = personService.getAllInterns();

        //Then
        assertEquals(null, interns);
    }


    @Test
    public void shouldGetPersonById() {
        // Given
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        Quiz quiz1 = new Quiz();
        quiz1.setId(2L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(3L);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);
        Person personToReturn = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);
        Mockito.when(personDAO.findPersonById(id)).thenReturn(Optional.of(personToReturn));

        // When
        PersonDTO person = personService.getPersonById(id);
        System.out.println(person.toString());

        //Then
        assertEquals(personToReturn.getId(), person.getId());
        assertEquals(personToReturn.getUsername(), person.getUsername());
        assertEquals(personToReturn.getPassword(), person.getPassword());
        assertEquals(personToReturn.getLastname(), person.getLastname());
        assertEquals(personToReturn.getFirstname(), person.getFirstname());
        assertEquals(personToReturn.getEmail(), person.getEmail());
        assertEquals(personToReturn.getCompany(), person.getCompany());
        assertEquals(personToReturn.getRole(), person.getRole());
        assertEquals(quizzesIds.length, person.getQuizzesIds().length);
    }

    @Test
    public void shouldNotGetPersonById() {
        // Given
        Long id = 1L;
        Mockito.when(personDAO.findPersonById(id)).thenReturn(Optional.empty());

        // When
        PersonDTO person = personService.getPersonById(id);

        //Then
        assertEquals(null, person);
    }

}
