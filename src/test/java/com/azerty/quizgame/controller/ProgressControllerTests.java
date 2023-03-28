package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.security.config.JwtService;
import com.azerty.quizgame.service.ProgressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
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
@WebMvcTest(controllers = ProgressController.class)
public class ProgressControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private ProgressService progressService;
    @MockBean
    private ProgressDAO progressDAO;
    @MockBean
    private PersonDAO personDAO;
    @MockBean
    private QuizDAO quizDAO;
    @MockBean
    private RecordDAO recordDAO;


    @Test
    public void shouldGetAllProgresses() throws Exception {
        ProgressDTO progress1 = new ProgressDTO();
        ProgressDTO progress2 = new ProgressDTO();
        ArrayList<ProgressDTO> progresses = new ArrayList<>(List.of(progress1, progress2));

        given(progressService.getAllProgresses()).willReturn(progresses);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(progresses.size())));
    }

    @Test
    public void shouldNotGetAllProgresses500() throws Exception {
        given(progressService.getAllProgresses()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetProgressById() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);
        String dateAndTimeOfCompletionAsString = progress.getDateAndTimeOfCompletion().toString();
        dateAndTimeOfCompletionAsString = dateAndTimeOfCompletionAsString.replaceAll("0+$", "");

        given(progressService.getProgressById(id)).willReturn(progress);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(progress.getId().toString()))))
                .andExpect(jsonPath("$.dateAndTimeOfCompletion", is(dateAndTimeOfCompletionAsString)))
                .andExpect(jsonPath("$.score", is(progress.getScore())))
                .andExpect(jsonPath("$.personId", is(Integer.valueOf(progress.getPersonId().toString()))))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(progress.getQuizId().toString()))))
                //.andExpect(jsonPath("$.recordsIds", is(Arrays.toString(progress.getRecordsIds()))))
                .andExpect(jsonPath("$.recordsIds[0]", is(Integer.valueOf(progress.getRecordsIds()[0].toString()))))
                .andExpect(jsonPath("$.recordsIds[1]", is(Integer.valueOf(progress.getRecordsIds()[1].toString()))))
                .andExpect(jsonPath("$.recordsIds[2]", is(Integer.valueOf(progress.getRecordsIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetProgressById404() throws Exception {
        Long id = 1L;
        given(progressService.getProgressById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetProgressById500() throws Exception {
        Long id = 1L;
        given(progressService.getProgressById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveProgress() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);
        String dateAndTimeOfCompletionAsString = progress.getDateAndTimeOfCompletion().toString();
        dateAndTimeOfCompletionAsString = dateAndTimeOfCompletionAsString.replaceAll("0+$", "");

        given(progressService.saveProgress(any())).willReturn(progress);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(progress.getId().toString()))))
                .andExpect(jsonPath("$.dateAndTimeOfCompletion", is(dateAndTimeOfCompletionAsString)))
                .andExpect(jsonPath("$.score", is(progress.getScore())))
                .andExpect(jsonPath("$.personId", is(Integer.valueOf(progress.getPersonId().toString()))))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(progress.getQuizId().toString()))))
                //.andExpect(jsonPath("$.recordsIds", is(Arrays.toString(progress.getRecordsIds()))))
                .andExpect(jsonPath("$.recordsIds[0]", is(Integer.valueOf(progress.getRecordsIds()[0].toString()))))
                .andExpect(jsonPath("$.recordsIds[1]", is(Integer.valueOf(progress.getRecordsIds()[1].toString()))))
                .andExpect(jsonPath("$.recordsIds[2]", is(Integer.valueOf(progress.getRecordsIds()[2].toString()))));
    }

    @Test
    public void shouldNotSaveProgress400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveProgress404Intern() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(personDAO.findById(personId)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveProgress404Quiz() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(quizDAO.findById(quizId)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveProgress404Record() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(recordDAO.findById(recordsIds[0])).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveProgress500() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(progressService.saveProgress(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/progresses/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateProgressById() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);
        String dateAndTimeOfCompletionAsString = progress.getDateAndTimeOfCompletion().toString();
        dateAndTimeOfCompletionAsString = dateAndTimeOfCompletionAsString.replaceAll("0+$", "");

        given(progressService.updateProgressById(any(), eq(id))).willReturn(progress);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/progresses/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(progress.getId().toString()))))
                .andExpect(jsonPath("$.dateAndTimeOfCompletion", is(dateAndTimeOfCompletionAsString)))
                .andExpect(jsonPath("$.score", is(progress.getScore())))
                .andExpect(jsonPath("$.personId", is(Integer.valueOf(progress.getPersonId().toString()))))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(progress.getQuizId().toString()))))
                //.andExpect(jsonPath("$.recordsIds", is(Arrays.toString(progress.getRecordsIds()))))
                .andExpect(jsonPath("$.recordsIds[0]", is(Integer.valueOf(progress.getRecordsIds()[0].toString()))))
                .andExpect(jsonPath("$.recordsIds[1]", is(Integer.valueOf(progress.getRecordsIds()[1].toString()))))
                .andExpect(jsonPath("$.recordsIds[2]", is(Integer.valueOf(progress.getRecordsIds()[2].toString()))));
    }

    @Test
    public void shouldNotUpdateProgressById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/progresses/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateProgressById404() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(progressDAO.findById(id)).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/progresses/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateProgressById500() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);

        given(progressService.updateProgressById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/progresses/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(progress)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldDeleteProgressById() throws Exception {
        Long id = 1L;
        given(progressService.deleteProgressById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteProgressById404() throws Exception {
        Long id = 1L;
        given(progressService.deleteProgressById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteProgressById500() throws Exception {
        Long id = 1L;
        given(progressService.deleteProgressById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/progresses/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetAllProgressesByPersonId() throws Exception {
        Long id = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetAllProgressesByPersonId404() throws Exception {
        Long id = 1L;

        given(progressService.getAllProgressesByPersonId(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllProgressesByPersonId500() throws Exception {
        Long id = 1L;
        given(progressService.getAllProgressesByPersonId(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetProgressByPersonIdAndQuizId() throws Exception {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 0;
        Long personId = 2L;
        Long quizId = 3L;
        Long[] recordsIds = {4L, 5L, 6L};
        ProgressDTO progress = new ProgressDTO(id, dateAndTimeOfCompletion, score, personId, quizId, recordsIds);
        String dateAndTimeOfCompletionAsString = progress.getDateAndTimeOfCompletion().toString();
        dateAndTimeOfCompletionAsString = dateAndTimeOfCompletionAsString.replaceAll("0+$", "");

        given(personDAO.findById(personId)).willReturn(Optional.of(new Person()));
        given(quizDAO.findById(personId)).willReturn(Optional.of(new Quiz()));
        given(progressService.getProgressByPersonIdAndQuizId(personId, quizId)).willReturn(progress);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{personId}/quiz/{quizId}", personId, quizId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(progress.getId().toString()))))
                .andExpect(jsonPath("$.dateAndTimeOfCompletion", is(dateAndTimeOfCompletionAsString)))
                .andExpect(jsonPath("$.score", is(progress.getScore())))
                .andExpect(jsonPath("$.personId", is(Integer.valueOf(progress.getPersonId().toString()))))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(progress.getQuizId().toString()))));
    }

    @Test
    public void shouldNotGetProgressByPersonIdAndQuizId404() throws Exception {
        Long personId = 1L;
        Long quizId = 2L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{personId}/quiz/{quizId}", personId, quizId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetProgressByPersonIdAndQuizId500() throws Exception {
        Long personId = 1L;
        Long quizId = 2L;
        given(progressService.getProgressByPersonIdAndQuizId(personId, quizId)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/progresses/intern/{personId}/quiz/{quizId}", personId, quizId))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


}
