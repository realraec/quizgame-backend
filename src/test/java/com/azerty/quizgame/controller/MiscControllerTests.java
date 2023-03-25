package com.azerty.quizgame.controller;

import com.azerty.quizgame.service.MiscService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MiscController.class)
public class MiscControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MiscService miscService;


    @Test
    public void shouldClearDatabase() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/clear"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotClearDatabase500() throws Exception {
        given(miscService.clearDatabase()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/clear"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldResetDatabase() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/reset"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotResetDatabase500() throws Exception {
        given(miscService.resetDatabase()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/reset"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
