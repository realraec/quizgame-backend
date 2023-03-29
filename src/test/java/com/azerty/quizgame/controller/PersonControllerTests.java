package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.enums.Role;
import com.azerty.quizgame.security.config.JwtService;
import com.azerty.quizgame.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private PersonService personService;
    @MockBean
    private PersonDAO personDAO;


    @Test
    public void shouldGetAllAdmins() throws Exception {
        PersonDTO admin1 = new PersonDTO();
        PersonDTO admin2 = new PersonDTO();
        ArrayList<PersonDTO> admins = new ArrayList<>(List.of(admin1, admin2));

        given(personService.getAllAdmins()).willReturn(admins);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/admins")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(admins.size())));
    }

    @Test
    public void shouldNotGetAllAdmins500() throws Exception {
        given(personService.getAllAdmins()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/admins"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldGetAllInterns() throws Exception {
        PersonDTO intern1 = new PersonDTO();
        PersonDTO intern2 = new PersonDTO();
        ArrayList<PersonDTO> interns = new ArrayList<>(List.of(intern1, intern2));

        given(personService.getAllInterns()).willReturn(interns);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/interns"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(interns.size())));
    }

    @Test
    public void shouldNotGetAllInterns500() throws Exception {
        given(personService.getAllInterns()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/interns"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetPersonById() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.getPersonById(id)).willReturn(person);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(person.getId().toString()))))
                .andExpect(jsonPath("$.username", is(person.getUsername())))
                .andExpect(jsonPath("$.password", is(person.getPassword())))
                .andExpect(jsonPath("$.lastname", is(person.getLastname())))
                .andExpect(jsonPath("$.firstname", is(person.getFirstname())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.company", is(person.getCompany())))
                .andExpect(jsonPath("$.role", is(person.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(person.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(person.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(person.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetPersonById404() throws Exception {
        Long id = 1L;
        given(personService.getPersonById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetPersonById500() throws Exception {
        Long id = 1L;
        given(personService.getPersonById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldGetPersonByUsername() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.getPersonByUsername(username)).willReturn(person);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/username/{username}", username))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(person.getId().toString()))))
                .andExpect(jsonPath("$.username", is(person.getUsername())))
                .andExpect(jsonPath("$.password", is(person.getPassword())))
                .andExpect(jsonPath("$.lastname", is(person.getLastname())))
                .andExpect(jsonPath("$.firstname", is(person.getFirstname())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.company", is(person.getCompany())))
                .andExpect(jsonPath("$.role", is(person.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(person.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(person.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(person.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetPersonByUsername404() throws Exception {
        String username = "intern1";
        given(personService.getPersonByUsername(username)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/username/{username}", username))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetPersonByUsername500() throws Exception {
        String username = "intern1";
        given(personService.getPersonByUsername(username)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/username/{username}", username))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSavePerson() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.savePerson(any())).willReturn(person);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/persons/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(person.getId().toString()))))
                .andExpect(jsonPath("$.username", is(person.getUsername())))
                .andExpect(jsonPath("$.password", is(person.getPassword())))
                .andExpect(jsonPath("$.lastname", is(person.getLastname())))
                .andExpect(jsonPath("$.firstname", is(person.getFirstname())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.company", is(person.getCompany())))
                .andExpect(jsonPath("$.role", is(person.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(person.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(person.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(person.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotSavePerson400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/persons/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSavePerson409() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.savePerson(any())).willThrow(DataIntegrityViolationException.class);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/persons/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldNotSavePerson500() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.savePerson(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/persons/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdatePersonById() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.updatePersonById(any(), eq(id))).willReturn(person);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/persons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(person.getId().toString()))))
                .andExpect(jsonPath("$.username", is(person.getUsername())))
                .andExpect(jsonPath("$.password", is(person.getPassword())))
                .andExpect(jsonPath("$.lastname", is(person.getLastname())))
                .andExpect(jsonPath("$.firstname", is(person.getFirstname())))
                .andExpect(jsonPath("$.email", is(person.getEmail())))
                .andExpect(jsonPath("$.role", is(person.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(person.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(person.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(person.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotUpdatePersonById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/persons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdatePersonById404() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personDAO.findById(id)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/persons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdatePersonById500() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        PersonDTO person = new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(personService.updatePersonById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/persons/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldDeletePersonById() throws Exception {
        Long id = 1L;
        given(personService.deletePersonById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeletePersonById404() throws Exception {
        Long id = 1L;
        given(personService.deletePersonById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeletePersonById500() throws Exception {
        Long id = 1L;
        given(personService.deletePersonById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/persons/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAllPersonsAttributedToQuizByQuizId() throws Exception {
        Long quizId = 1L;
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        ArrayList<PersonDTO> persons = new ArrayList<>(List.of(person1, person2));

        given(personService.getAllPersonsAttributedToQuizByQuizId(quizId)).willReturn(persons);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/attributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(persons.size())));
    }

    @Test
    public void shouldNotGetAllPersonsAttributedToQuizByQuizId404() throws Exception {
        Long quizId = 1L;

        given(personService.getAllPersonsAttributedToQuizByQuizId(quizId)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/attributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllPersonsAttributedToQuizByQuizId500() throws Exception {
        Long quizId = 1L;

        given(personService.getAllPersonsAttributedToQuizByQuizId(quizId)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/attributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAllPersonsNotAttributedToQuizByQuizId() throws Exception {
        Long quizId = 1L;
        PersonDTO person1 = new PersonDTO();
        PersonDTO person2 = new PersonDTO();
        ArrayList<PersonDTO> persons = new ArrayList<>(List.of(person1, person2));

        given(personService.getAllPersonsNotAttributedToQuizByQuizId(quizId)).willReturn(persons);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/notAttributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(persons.size())));
    }

    @Test
    public void shouldNotGetAllPersonsNotAttributedToQuizByQuizId404() throws Exception {
        Long quizId = 1L;

        given(personService.getAllPersonsNotAttributedToQuizByQuizId(quizId)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/notAttributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllPersonsNotAttributedToQuizByQuizId500() throws Exception {
        Long quizId = 1L;

        given(personService.getAllPersonsNotAttributedToQuizByQuizId(quizId)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/notAttributedToQuiz/{quizId}", quizId))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
