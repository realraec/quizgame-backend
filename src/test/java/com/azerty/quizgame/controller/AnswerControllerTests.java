package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.service.AnswerService;
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

@WebMvcTest(controllers = AnswerController.class)
public class AnswerControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerDAO answerDAO;
    @MockBean
    private QuestionDAO questionDAO;


    @Test
    public void shouldGetAllAnswers() throws Exception {
        AnswerDTO answer1 = new AnswerDTO();
        AnswerDTO answer2 = new AnswerDTO();
        ArrayList<AnswerDTO> answers = new ArrayList<>(List.of(answer1, answer2));

        given(answerService.getAllAnswers()).willReturn(answers);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(answers.size())));
    }

    @Test
    public void shouldNotGetAllAnswers500() throws Exception {
        given(answerService.getAllAnswers()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAnswerById() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerService.getAnswerById(id)).willReturn(answer);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(answer.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(answer.getWording())))
                .andExpect(jsonPath("$.correct", is(answer.isCorrect())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(answer.getQuestionId().toString()))));
    }

    @Test
    public void shouldNotGetAnswerById404() throws Exception {
        Long id = 1L;
        given(answerService.getAnswerById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAnswerById500() throws Exception {
        Long id = 1L;
        given(answerService.getAnswerById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveAnswer() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerService.saveAnswer(any())).willReturn(answer);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/answers/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(answer.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(answer.getWording())))
                .andExpect(jsonPath("$.correct", is(answer.isCorrect())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(answer.getQuestionId().toString()))));
    }

    @Test
    public void shouldNotSaveAnswer400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/answers/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveAnswer404() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        //given(answerService.saveAnswer(any())).willReturn(answer);
        given(questionDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/answers/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveAnswer500() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerService.saveAnswer(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/answers/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateAnswerById() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerService.updateAnswerById(any(), eq(id))).willReturn(answer);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(answer.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(answer.getWording())))
                .andExpect(jsonPath("$.correct", is(answer.isCorrect())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(answer.getQuestionId().toString()))));
    }

    @Test
    public void shouldNotUpdateAnswer400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateAnswerById404() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateAnswerById500() throws Exception {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Long questionId = 2L;
        AnswerDTO answer = new AnswerDTO(id, wording, isCorrect, questionId);

        given(answerService.updateAnswerById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(answer)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldDeleteAnswerById() throws Exception {
        Long id = 1L;
        given(answerService.deleteAnswerById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteAnswerById404() throws Exception {
        Long id = 1L;
        given(answerService.deleteAnswerById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteAnswerById500() throws Exception {
        Long id = 1L;
        given(answerService.deleteAnswerById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/answers/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAllAnswersByQuestionId() throws Exception {
        Long id = 1L;
        List<AnswerDTO> answers = new ArrayList<>();
        Long id1 = 2L;
        String wording1 = "The number 42.";
        boolean isCorrect1 = true;
        Long questionId1 = 3L;
        AnswerDTO answer1 = new AnswerDTO(id1, wording1, isCorrect1, questionId1);
        Long id2 = 4L;
        String wording2 = "Not the number 42.";
        boolean isCorrect2 = false;
        Long questionId2 = 5L;
        AnswerDTO answer2 = new AnswerDTO(id2, wording2, isCorrect2, questionId2);
        answers.add(answer1);
        answers.add(answer2);

        given(answerService.getAllAnswersByQuestionId(id)).willReturn(answers);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/question/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(answers.size())))
                .andExpect(jsonPath("$[0].id", is(Integer.valueOf(id1.toString()))))
                .andExpect(jsonPath("$[0].wording", is(wording1)))
                .andExpect(jsonPath("$[0].correct", is(isCorrect1)))
                .andExpect(jsonPath("$[0].questionId", is(Integer.valueOf(questionId1.toString()))))
                .andExpect(jsonPath("$[1].id", is(Integer.valueOf(id2.toString()))))
                .andExpect(jsonPath("$[1].wording", is(wording2)))
                .andExpect(jsonPath("$[1].correct", is(isCorrect2)))
                .andExpect(jsonPath("$[1].questionId", is(Integer.valueOf(questionId2.toString()))));
    }

    @Test
    public void shouldNotGetAllAnswersByQuestionId404() throws Exception {
        Long id = 1L;
        given(questionDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/question/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllAnswersByQuestionId500() throws Exception {
        Long id = 1L;
        given(answerService.getAllAnswersByQuestionId(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/answers/question/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
