package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.InternDAO;
import com.azerty.quizgame.model.dto.InternDTO;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import com.azerty.quizgame.service.InternService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@WebMvcTest(controllers = InternController.class)
public class InternControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private InternService internService;
    @MockBean
    private InternDAO internDAO;


    @Test
    public void shouldGetAllInterns() throws Exception {
        InternDTO intern1 = new InternDTO();
        InternDTO intern2 = new InternDTO();
        ArrayList<InternDTO> interns = new ArrayList<>(List.of(intern1, intern2));

        given(internService.getAllInterns()).willReturn(interns);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/interns"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(interns.size())));
    }

    @Test
    public void shouldNotGetAllInterns500() throws Exception {
        given(internService.getAllInterns()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/interns"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetInternById() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internService.getInternById(id)).willReturn(intern);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(intern.getId().toString()))))
                .andExpect(jsonPath("$.username", is(intern.getUsername())))
                .andExpect(jsonPath("$.password", is(intern.getPassword())))
                .andExpect(jsonPath("$.lastname", is(intern.getLastname())))
                .andExpect(jsonPath("$.firstname", is(intern.getFirstname())))
                .andExpect(jsonPath("$.email", is(intern.getEmail())))
                .andExpect(jsonPath("$.company", is(intern.getCompany())))
                .andExpect(jsonPath("$.role", is(intern.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(intern.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(intern.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(intern.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetInternById404() throws Exception {
        Long id = 1L;
        given(internService.getInternById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetInternById500() throws Exception {
        Long id = 1L;
        given(internService.getInternById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveIntern() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internService.saveIntern(any())).willReturn(intern);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/interns/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(intern)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(intern.getId().toString()))))
                .andExpect(jsonPath("$.username", is(intern.getUsername())))
                .andExpect(jsonPath("$.password", is(intern.getPassword())))
                .andExpect(jsonPath("$.lastname", is(intern.getLastname())))
                .andExpect(jsonPath("$.firstname", is(intern.getFirstname())))
                .andExpect(jsonPath("$.email", is(intern.getEmail())))
                .andExpect(jsonPath("$.company", is(intern.getCompany())))
                .andExpect(jsonPath("$.role", is(intern.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(intern.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(intern.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(intern.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotSaveIntern400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/interns/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveIntern500() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internService.saveIntern(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/interns/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(intern)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateInternById() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internService.updateInternById(any(), eq(id))).willReturn(intern);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/interns/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(intern)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(intern.getId().toString()))))
                .andExpect(jsonPath("$.username", is(intern.getUsername())))
                .andExpect(jsonPath("$.password", is(intern.getPassword())))
                .andExpect(jsonPath("$.lastname", is(intern.getLastname())))
                .andExpect(jsonPath("$.firstname", is(intern.getFirstname())))
                .andExpect(jsonPath("$.email", is(intern.getEmail())))
                .andExpect(jsonPath("$.role", is(intern.getRole().toString())))
                .andExpect(jsonPath("$.quizzesIds[0]", is(Integer.valueOf(intern.getQuizzesIds()[0].toString()))))
                .andExpect(jsonPath("$.quizzesIds[1]", is(Integer.valueOf(intern.getQuizzesIds()[1].toString()))))
                .andExpect(jsonPath("$.quizzesIds[2]", is(Integer.valueOf(intern.getQuizzesIds()[2].toString()))));
    }

    @Test
    public void shouldNotUpdateIntern400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/interns/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateInternById404() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/interns/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(intern)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateInternById500() throws Exception {
        Long id = 1L;
        String username = "intern1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.INTERN;
        String company = "McDonald's";
        Long[] quizzesIds = {2L, 3L, 4L};
        InternDTO intern = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);

        given(internService.updateInternById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/interns/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(intern)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldDeleteInternById() throws Exception {
        Long id = 1L;
        given(internService.deleteInternById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteInternById404() throws Exception {
        Long id = 1L;
        given(internService.deleteInternById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteInternById500() throws Exception {
        Long id = 1L;
        given(internService.deleteInternById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/interns/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
