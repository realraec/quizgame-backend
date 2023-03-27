package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import com.azerty.quizgame.utils.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class PersonServiceTests {

    @InjectMocks
    private PersonServiceImplementation personService;
    @Mock
    private PersonDAO personDAO;
    @Mock
    private QuizDAO quizDAO;

    private final PersonMapper personMapper = new PersonMapper();


    @Test
    public void shouldGetAllAdmins() {
        // Given
        Long id1 = 1L;
        String username1 = "user1";
        String password1 = "P@ssword1";
        String lastname1 = "Tyson";
        String firstname1 = "Mike";
        String email1 = "mike.tyson@gmail.com";
        Role role1 = Role.ADMIN;
        List<Quiz> quizzes1 = new ArrayList<>();
        Person admin1 = new Person(id1, username1, password1, lastname1, firstname1, email1, null, role1, quizzes1);

        Long id2 = 1L;
        String username2 = "user2";
        String password2 = "P@ssword2";
        String lastname2 = "Ali";
        String firstname2 = "Muhammad";
        String email2 = "muhammad.ali@gmail.com";
        Role role2 = Role.ADMIN;
        List<Quiz> quizzes2 = new ArrayList<>();
        Person admin2 = new Person(id2, username2, password2, lastname2, firstname2, email2, null, role2, quizzes2);

        List<Person> adminsToReturn = new ArrayList<>();
        adminsToReturn.add(admin1);
        adminsToReturn.add(admin2);
        Mockito.when(personDAO.findAllPersonsWithRoleAdmin()).thenReturn(adminsToReturn);

        // When
        List<PersonDTO> admins = personService.getAllAdmins();

        // Then
        assertEquals(adminsToReturn.size(), admins.size());
    }

    @Test
    public void shouldNotGetAllAdmins() {
        // Given
        Mockito.when(personDAO.findAllPersonsWithRoleAdmin()).thenReturn(null);

        // When
        List<PersonDTO> admins = personService.getAllAdmins();

        // Then
        assertNull(admins);
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
        Role role1 = Role.INTERN;
        List<Quiz> quizzes1 = new ArrayList<>();
        quizzes1.add(new Quiz());
        quizzes1.add(new Quiz());
        Person intern1 = new Person(id1, username1, password1, lastname1, firstname1, email1, null, role1, quizzes1);

        Long id2 = 2L;
        String username2 = "user2";
        String password2 = "P@ssword2";
        String lastname2 = "Ali";
        String firstname2 = "Muhammad";
        String email2 = "muhammad.ali@gmail.com";
        Role role2 = Role.INTERN;
        List<Quiz> quizzes2 = new ArrayList<>();
        quizzes2.add(new Quiz());
        quizzes2.add(new Quiz());
        Person intern2 = new Person(id2, username2, password2, lastname2, firstname2, email2, null, role2, quizzes2);

        List<Person> internsToReturn = new ArrayList<>();
        internsToReturn.add(intern1);
        internsToReturn.add(intern2);
        Mockito.when(personDAO.findAllPersonsWithRoleIntern()).thenReturn(internsToReturn);

        // When
        List<PersonDTO> interns = personService.getAllInterns();

        // Then
        assertEquals(internsToReturn.size(), interns.size());
    }

    @Test
    public void shouldNotGetAllInterns() {
        // Given
        Mockito.when(personDAO.findAllPersonsWithRoleIntern()).thenReturn(null);

        // When
        List<PersonDTO> interns = personService.getAllInterns();

        // Then
        assertNull(interns);
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
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.of(personToReturn));

        // When
        PersonDTO person = personService.getPersonById(id);

        // Then
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
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.empty());

        // When
        PersonDTO person = personService.getPersonById(id);

        // Then
        assertNull(person);
    }

    @Test
    public void shouldSaveAnswer() {
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
        Mockito.when(quizDAO.findById(quizzesIds[0])).thenReturn(Optional.of(quiz1));
        Mockito.when(quizDAO.findById(quizzesIds[1])).thenReturn(Optional.of(quiz2));
        Mockito.when(personDAO.save(any())).thenReturn(personToReturn);

        // When
        PersonDTO person = personService.savePerson(personMapper.toPersonDTO(personToReturn));

        // Then
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
    public void shouldSaveAnswerNoQuizzes() {
        // Given
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);
        Person personToReturn = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);
        Mockito.when(personDAO.save(any())).thenReturn(personToReturn);

        // When
        PersonDTO person = personService.savePerson(personMapper.toPersonDTO(personToReturn));

        // Then
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
    public void shouldNotSaveAnswer() {
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
        Long quizId1 = 2L;
        quiz1.setId(quizId1);
        Quiz quiz2 = new Quiz();
        Long quizId2 = 3L;
        quiz2.setId(quizId2);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);
        Person personToReturn = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);
        Mockito.when(quizDAO.findById(quizId1)).thenReturn(Optional.empty());

        // When
        PersonDTO person = personService.savePerson(personMapper.toPersonDTO(personToReturn));

        // Then
        assertNull(person);
    }

    @Test
    public void shouldDeletePersonById() {
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
        Person personToReturn = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.of(personToReturn));

        // When
        boolean isDeleted = personService.deletePersonById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeletePersonById() {
        // Given
        Long id = 1L;
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = personService.deletePersonById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdatePersonById() {
        // Given
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);
        Person personToReturn = new Person(id, username, password, lastname, firstname, email, company, role, quizzes);
        String company2 = "KFC";
        Person updatedPerson = new Person(id, username, password, lastname, firstname, email, company2, role, quizzes);
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.of(personToReturn));
        Mockito.when(personDAO.save(any())).thenReturn(updatedPerson);

        // When
        PersonDTO person = personService.updatePersonById(personMapper.toPersonDTO(updatedPerson), id);

        // Then
        assertEquals(updatedPerson.getId(), person.getId());
        assertEquals(updatedPerson.getUsername(), person.getUsername());
        assertEquals(updatedPerson.getPassword(), person.getPassword());
        assertEquals(updatedPerson.getLastname(), person.getLastname());
        assertEquals(updatedPerson.getFirstname(), person.getFirstname());
        assertEquals(updatedPerson.getEmail(), person.getEmail());
        assertEquals(updatedPerson.getCompany(), person.getCompany());
        assertEquals(updatedPerson.getRole(), person.getRole());
        assertEquals(quizzesIds.length, person.getQuizzesIds().length);
    }

    @Test
    public void shouldNotUpdatePersonById() {
        // Given
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        Quiz quiz1 = new Quiz();
        quiz1.setId(2L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(3L);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);
        String company2 = "KFC";
        Person updatedPerson = new Person(id, username, password, lastname, firstname, email, company2, role, quizzes);
        Mockito.when(personDAO.findById(id)).thenReturn(Optional.empty());

        // When
        PersonDTO person = personService.updatePersonById(personMapper.toPersonDTO(updatedPerson), id);

        // Then
        assertNull(person);
    }

    @Test
    public void shouldGetAllPersonsAttributedToQuizByQuizId() {
        // Given
        Long quizId = 1L;
        Quiz quizToReturn = new Quiz();
        quizToReturn.setId(quizId);
        Person person1 = new Person();
        person1.setQuizzes(new ArrayList<>(List.of(quizToReturn)));
        Person person2 = new Person();
        person2.setQuizzes(new ArrayList<>(List.of(quizToReturn)));
        List<Person> personsToReturn = new ArrayList<>(List.of(person1, person2));

        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findAllPersonsAttributedToQuizByQuizId(quizId)).thenReturn(personsToReturn);

        // When
        List<PersonDTO> persons = personService.getAllPersonsAttributedToQuizByQuizId(quizId);

        // Then
        assertEquals(personsToReturn.size(), persons.size());
    }

    @Test
    public void shouldNotGetAllPersonsAttributedToQuizByQuizId() {
        // Given
        Long quizId = 1L;
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        List<PersonDTO> persons = personService.getAllPersonsAttributedToQuizByQuizId(quizId);

        // Then
        assertNull(persons);
    }

    @Test
    public void shouldGetAllPersonsNotAttributedToQuizByQuizId() {
        // Given
        Long quizId = 1L;
        Quiz quizToReturn = new Quiz();
        quizToReturn.setId(quizId);
        Person person1 = new Person();
        person1.setQuizzes(new ArrayList<>());
        Person person2 = new Person();
        person2.setQuizzes(new ArrayList<>());
        List<Person> personsToReturn = new ArrayList<>(List.of(person1, person2));

        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findAllPersonsNotAttributedToQuizByQuizId(quizId)).thenReturn(personsToReturn);

        // When
        List<PersonDTO> persons = personService.getAllPersonsNotAttributedToQuizByQuizId(quizId);

        // Then
        assertEquals(personsToReturn.size(), persons.size());
    }

    @Test
    public void shouldNotGetAllPersonsNotAttributedToQuizByQuizId() {
        // Given
        Long quizId = 1L;
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        List<PersonDTO> persons = personService.getAllPersonsNotAttributedToQuizByQuizId(quizId);

        // Then
        assertNull(persons);
    }

}
