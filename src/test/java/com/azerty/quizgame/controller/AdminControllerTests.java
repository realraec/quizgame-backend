package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.AdminDAO;
import com.azerty.quizgame.model.dto.AdminDTO;
import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.model.enums.Role;
import com.azerty.quizgame.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
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

@WebMvcTest(controllers = AdminController.class)
public class AdminControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AdminService adminService;
    @MockBean
    private AdminDAO adminDAO;


    @Test
    public void shouldGetAllAdmins() throws Exception {
        AdminDTO admin1 = new AdminDTO();
        AdminDTO admin2 = new AdminDTO();
        ArrayList<AdminDTO> admins = new ArrayList<>(List.of(admin1, admin2));

        given(adminService.getAllAdmins()).willReturn(admins);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(admins.size())));
    }

    @Test
    public void shouldNotGetAllAdmins500() throws Exception {
        given(adminService.getAllAdmins()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAdminById() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.getAdminById(id)).willReturn(admin);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(admin.getId().toString()))))
                .andExpect(jsonPath("$.username", is(admin.getUsername())))
                .andExpect(jsonPath("$.password", is(admin.getPassword())))
                .andExpect(jsonPath("$.lastname", is(admin.getLastname())))
                .andExpect(jsonPath("$.firstname", is(admin.getFirstname())))
                .andExpect(jsonPath("$.email", is(admin.getEmail())))
                .andExpect(jsonPath("$.role", is(admin.getRole().toString())));
    }

    @Test
    public void shouldNotGetAdminById404() throws Exception {
        Long id = 1L;
        given(adminService.getAdminById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAdminById500() throws Exception {
        Long id = 1L;
        given(adminService.getAdminById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveAdmin() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.saveAdmin(any())).willReturn(admin);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/admins/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(admin.getId().toString()))))
                .andExpect(jsonPath("$.username", is(admin.getUsername())))
                .andExpect(jsonPath("$.password", is(admin.getPassword())))
                .andExpect(jsonPath("$.lastname", is(admin.getLastname())))
                .andExpect(jsonPath("$.firstname", is(admin.getFirstname())))
                .andExpect(jsonPath("$.email", is(admin.getEmail())))
                .andExpect(jsonPath("$.role", is(admin.getRole().toString())));
    }


    @Test
    public void shouldNotSaveAdmin400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/admins/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveAdmin409() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.saveAdmin(any())).willThrow(DataIntegrityViolationException.class);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/admins/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldNotSaveAdmin500() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.saveAdmin(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/admins/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateAdminById() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.updateAdminById(any(), eq(id))).willReturn(admin);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/admins/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(admin.getId().toString()))))
                .andExpect(jsonPath("$.username", is(admin.getUsername())))
                .andExpect(jsonPath("$.password", is(admin.getPassword())))
                .andExpect(jsonPath("$.lastname", is(admin.getLastname())))
                .andExpect(jsonPath("$.firstname", is(admin.getFirstname())))
                .andExpect(jsonPath("$.email", is(admin.getEmail())))
                .andExpect(jsonPath("$.role", is(admin.getRole().toString())));
    }

    @Test
    public void shouldNotUpdateAdminById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/admins/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateAdminById404() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/admins/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateAdminById500() throws Exception {
        Long id = 1L;
        String username = "admin1";
        String password = "P@ssW0rd1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        Role role = Role.ADMIN;
        AdminDTO admin = new AdminDTO(id, username, password, lastname, firstname, email, role);

        given(adminService.updateAdminById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/admins/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldDeleteAdminById() throws Exception {
        Long id = 1L;
        given(adminService.deleteAdminById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteAdminById404() throws Exception {
        Long id = 1L;
        given(adminService.deleteAdminById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteAdminById500() throws Exception {
        Long id = 1L;
        given(adminService.deleteAdminById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/admins/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldGetCounts() throws Exception {
        CountsDTO counts = new CountsDTO(20L, 5L);
        given(adminService.getInternCountAndQuizCount()).willReturn(counts);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins/counts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.internCount", is(Integer.valueOf(counts.getInternCount().toString()))))
                .andExpect(jsonPath("$.quizCount", is(Integer.valueOf(counts.getQuizCount().toString()))));
    }

    @Test
    public void shouldNotGetCounts500() throws Exception {
        Long id = 1L;
        given(adminService.getInternCountAndQuizCount()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/admins/counts"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
