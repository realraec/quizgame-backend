package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.enums.QuizState;
import com.azerty.quizgame.service.QuizService;
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

@WebMvcTest(controllers = QuizController.class)
public class QuizControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private QuizService quizService;
    @MockBean
    private QuizDAO quizDAO;
    @MockBean
    private QuestionDAO questionDAO;


    @Test
    public void shouldGetAllQuizzes() throws Exception {
        QuizDTO quiz1 = new QuizDTO();
        QuizDTO quiz2 = new QuizDTO();
        ArrayList<QuizDTO> quizzes = new ArrayList<>(List.of(quiz1, quiz2));

        given(quizService.getAllQuizzes()).willReturn(quizzes);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(quizzes.size())));
    }

    @Test
    public void shouldNotGetAllQuizzes500() throws Exception {
        given(quizService.getAllQuizzes()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetQuizById() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.getQuizById(id)).willReturn(quiz);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(quiz.getId().toString()))))
                .andExpect(jsonPath("$.title", is(quiz.getTitle())))
                .andExpect(jsonPath("$.summary", is(quiz.getSummary())))
                //.andExpect(jsonPath("$.questionsIds", is(Arrays.toString(quiz.getQuestionsIds()))))
                .andExpect(jsonPath("$.questionsIds[0]", is(Integer.valueOf(quiz.getQuestionsIds()[0].toString()))))
                .andExpect(jsonPath("$.questionsIds[1]", is(Integer.valueOf(quiz.getQuestionsIds()[1].toString()))))
                .andExpect(jsonPath("$.questionsIds[2]", is(Integer.valueOf(quiz.getQuestionsIds()[2].toString()))))
                .andExpect(jsonPath("$.personsIds[0]", is(Integer.valueOf(quiz.getPersonsIds()[0].toString()))))
                .andExpect(jsonPath("$.personsIds[1]", is(Integer.valueOf(quiz.getPersonsIds()[1].toString()))))
                .andExpect(jsonPath("$.personsIds[2]", is(Integer.valueOf(quiz.getPersonsIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetQuizById404() throws Exception {
        Long id = 1L;
        given(quizService.getQuizById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetQuizById500() throws Exception {
        Long id = 1L;
        given(quizService.getQuizById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveQuiz() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.saveQuiz(any())).willReturn(quiz);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/quizzes/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(quiz.getId().toString()))))
                .andExpect(jsonPath("$.title", is(quiz.getTitle())))
                .andExpect(jsonPath("$.summary", is(quiz.getSummary())))
                //.andExpect(jsonPath("$.questionsIds", is(Arrays.toString(quiz.getQuestionsIds()))))
                .andExpect(jsonPath("$.questionsIds[0]", is(Integer.valueOf(quiz.getQuestionsIds()[0].toString()))))
                .andExpect(jsonPath("$.questionsIds[1]", is(Integer.valueOf(quiz.getQuestionsIds()[1].toString()))))
                .andExpect(jsonPath("$.questionsIds[2]", is(Integer.valueOf(quiz.getQuestionsIds()[2].toString()))))
                .andExpect(jsonPath("$.personsIds[0]", is(Integer.valueOf(quiz.getPersonsIds()[0].toString()))))
                .andExpect(jsonPath("$.personsIds[1]", is(Integer.valueOf(quiz.getPersonsIds()[1].toString()))))
                .andExpect(jsonPath("$.personsIds[2]", is(Integer.valueOf(quiz.getPersonsIds()[2].toString()))));
    }

    @Test
    public void shouldNotSaveQuiz400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/quizzes/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveQuiz404() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(questionDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/quizzes/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveQuiz500() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.saveQuiz(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/quizzes/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateQuizById() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.updateQuizById(any(), eq(id))).willReturn(quiz);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/quizzes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(quiz.getId().toString()))))
                .andExpect(jsonPath("$.title", is(quiz.getTitle())))
                .andExpect(jsonPath("$.summary", is(quiz.getSummary())))
                //.andExpect(jsonPath("$.questionsIds", is(Arrays.toString(quiz.getQuestionsIds()))))
                .andExpect(jsonPath("$.questionsIds[0]", is(Integer.valueOf(quiz.getQuestionsIds()[0].toString()))))
                .andExpect(jsonPath("$.questionsIds[1]", is(Integer.valueOf(quiz.getQuestionsIds()[1].toString()))))
                .andExpect(jsonPath("$.questionsIds[2]", is(Integer.valueOf(quiz.getQuestionsIds()[2].toString()))))
                .andExpect(jsonPath("$.personsIds[0]", is(Integer.valueOf(quiz.getPersonsIds()[0].toString()))))
                .andExpect(jsonPath("$.personsIds[1]", is(Integer.valueOf(quiz.getPersonsIds()[1].toString()))))
                .andExpect(jsonPath("$.personsIds[2]", is(Integer.valueOf(quiz.getPersonsIds()[2].toString()))));
    }

    @Test
    public void shouldNotUpdateQuizById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/quizzes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateQuizById404() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/quizzes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateQuizById500() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.updateQuizById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/quizzes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldDeleteQuizById() throws Exception {
        Long id = 1L;
        given(quizService.deleteQuizById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteQuizById404() throws Exception {
        Long id = 1L;
        given(quizService.deleteQuizById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteQuizById500() throws Exception {
        Long id = 1L;
        given(quizService.deleteQuizById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/quizzes/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetQuizWithStateByQuizIdAndPersonId() throws Exception {
        Long quizId = 1L;
        Long personId = 1L;

        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        QuizState state = QuizState.STARTED;
        QuizForInternDTO quiz = new QuizForInternDTO(id, title, summary, state);

        given(quizService.getQuizWithStateByQuizIdAndPersonId(quizId, personId)).willReturn(quiz);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{quizId}/forIntern/{personId}", quizId, personId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(quiz.getId().toString()))))
                .andExpect(jsonPath("$.title", is(quiz.getTitle())))
                .andExpect(jsonPath("$.summary", is(quiz.getSummary())))
                .andExpect(jsonPath("$.state", is(quiz.getState().toString())));
    }

    @Test
    public void shouldNotGetQuizWithStateByQuizIdAndPersonId404() throws Exception {
        Long quizId = 1L;
        Long personId = 1L;
        given(quizService.getQuizWithStateByQuizIdAndPersonId(quizId, personId)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{quizId}/forIntern/{personId}", quizId, personId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetQuizWithStateByQuizIdAndPersonId500() throws Exception {
        Long quizId = 1L;
        Long personId = 1L;
        given(quizService.getQuizWithStateByQuizIdAndPersonId(quizId, personId)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/{quizId}/forIntern/{personId}", quizId, personId))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAllQuizzesAttributedToPersonWithStateByPersonId() throws Exception {
        Long personId = 1L;

        Long id1 = 1L;
        String title1 = "Life";
        String summary1 = "Pretty important stuff.";
        QuizState state1 = QuizState.STARTED;
        QuizForInternDTO quiz1 = new QuizForInternDTO(id1, title1, summary1, state1);
        Long id2 = 1L;
        String title2 = "Death";
        String summary2 = "Pretty gloomy stuff.";
        QuizState state2 = QuizState.NOT_STARTED;
        QuizForInternDTO quiz2 = new QuizForInternDTO(id2, title2, summary2, state2);
        List<QuizForInternDTO> quizzes = new ArrayList<>();
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        given(quizService.getAllQuizzesAttributedToPersonWithStateByPersonId(personId)).willReturn(quizzes);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/forIntern/{personId}", personId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(quizzes.size())))
                .andExpect(jsonPath("$[0].id", is(Integer.valueOf(quiz1.getId().toString()))))
                .andExpect(jsonPath("$[0].title", is(quiz1.getTitle())))
                .andExpect(jsonPath("$[0].summary", is(quiz1.getSummary())))
                .andExpect(jsonPath("$[0].state", is(quiz1.getState().toString())))
                .andExpect(jsonPath("$[1].id", is(Integer.valueOf(quiz2.getId().toString()))))
                .andExpect(jsonPath("$[1].title", is(quiz2.getTitle())))
                .andExpect(jsonPath("$[1].summary", is(quiz2.getSummary())))
                .andExpect(jsonPath("$[1].state", is(quiz2.getState().toString())));
    }

    @Test
    public void shouldNotGetAllQuizzesAttributedToPersonWithStateByPersonId404() throws Exception {
        Long personId = 1L;
        given(quizService.getAllQuizzesAttributedToPersonWithStateByPersonId(personId)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/forIntern/{personId}", personId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllQuizzesAttributedToPersonWithStateByPersonId500() throws Exception {
        Long personId = 1L;
        given(quizService.getAllQuizzesAttributedToPersonWithStateByPersonId(personId)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/quizzes/forIntern/{personId}", personId))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldAttributePersonsToQuizByIds() throws Exception {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty important stuff.";
        Long[] questionsIds = {2L, 3L, 4L};
        Long[] personsIds = {5L, 6L, 7L};
        String personsIdsAsString = "5,6,7";
        QuizDTO quiz = new QuizDTO(id, title, summary, questionsIds, personsIds);

        given(quizService.attributePersonsToQuizByIds(any(), any())).willReturn(quiz);

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/quizzes/{quizId}/attributePersons/{personsIdsAsString}", id, personsIdsAsString)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(quiz)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(quiz.getId().toString()))))
                .andExpect(jsonPath("$.title", is(quiz.getTitle())))
                .andExpect(jsonPath("$.summary", is(quiz.getSummary())))
                //.andExpect(jsonPath("$.questionsIds", is(Arrays.toString(quiz.getQuestionsIds()))))
                .andExpect(jsonPath("$.questionsIds[0]", is(Integer.valueOf(quiz.getQuestionsIds()[0].toString()))))
                .andExpect(jsonPath("$.questionsIds[1]", is(Integer.valueOf(quiz.getQuestionsIds()[1].toString()))))
                .andExpect(jsonPath("$.questionsIds[2]", is(Integer.valueOf(quiz.getQuestionsIds()[2].toString()))))
                .andExpect(jsonPath("$.personsIds[0]", is(Integer.valueOf(quiz.getPersonsIds()[0].toString()))))
                .andExpect(jsonPath("$.personsIds[1]", is(Integer.valueOf(quiz.getPersonsIds()[1].toString()))))
                .andExpect(jsonPath("$.personsIds[2]", is(Integer.valueOf(quiz.getPersonsIds()[2].toString()))));
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIds400() throws Exception {
        Long quizId = 1L;
        String personsIds = "xxx";

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/quizzes/{quizId}/attributePersons/{personsIds}", quizId, personsIds))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAttributePersonsToQuizById404() throws Exception {
        Long quizId = 1L;
        //Long[] personsIds = {102L, 103L, 104L};
        String personsIdsAsString = "102,103,104";

        given(quizService.attributePersonsToQuizByIds(any(), any())).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/quizzes/{quizId}/attributePersons/{personsIdsAsString}", quizId, personsIdsAsString))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotAttributePersonsToQuizById409() throws Exception {
        Long quizId = 1L;
        //Long[] personsIds = {2L, 3L, 4L};
        String personsIdsAsString = "2,3,4";

        given(quizService.attributePersonsToQuizByIds(any(), any())).willReturn(new QuizDTO());

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/quizzes/{quizId}/attributePersons/{personsIdsAsString}", quizId, personsIdsAsString))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIds500() throws Exception {
        Long quizId = 1L;
        //Long[] personsIds = {2L, 3L, 4L};
        String personsIdsAsString = "2,3,4";

        given(quizService.attributePersonsToQuizByIds(any(), any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .patch("/api/quizzes/{quizId}/attributePersons/{personsIdsAsString}", quizId, personsIdsAsString))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
