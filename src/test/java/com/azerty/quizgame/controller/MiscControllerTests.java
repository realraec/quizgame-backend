package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.service.MiscService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MiscController.class)
public class MiscControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MiscService miscService;


    @Test
    public void shouldGetCounts() throws Exception {
        CountsDTO counts = new CountsDTO(20L, 5L);
        given(miscService.getInternCountAndQuizCount()).willReturn(counts);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/counts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.internCount", is(Integer.valueOf(counts.getInternCount().toString()))))
                .andExpect(jsonPath("$.quizCount", is(Integer.valueOf(counts.getQuizCount().toString()))));
    }

    @Test
    public void shouldNotGetCounts500() throws Exception {
        given(miscService.getInternCountAndQuizCount()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/misc/counts"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

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
