package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.service.ProgressService;
import com.azerty.quizgame.service.RecordService;
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

@WebMvcTest(controllers = RecordController.class)
public class RecordControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RecordService recordService;
    @MockBean
    private ProgressService progressService;
    @MockBean
    private RecordDAO recordDAO;
    @MockBean
    private QuestionDAO questionDAO;
    @MockBean
    private ProgressDAO progressDAO;


    @Test
    public void shouldGetAllRecords() throws Exception {
        RecordDTO record1 = new RecordDTO();
        RecordDTO record2 = new RecordDTO();
        ArrayList<RecordDTO> records = new ArrayList<>(List.of(record1, record2));

        given(recordService.getAllRecords()).willReturn(records);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/records")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(records.size())));
    }

    @Test
    public void shouldNotGetAllRecords500() throws Exception {
        given(recordService.getAllRecords()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/records")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetRecordById() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.getRecordById(id)).willReturn(record);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(record.getId().toString()))))
                .andExpect(jsonPath("$.success", is(record.isSuccess())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(record.getQuestionId().toString()))))
                .andExpect(jsonPath("$.progressId", is(Integer.valueOf(record.getProgressId().toString()))));
    }

    @Test
    public void shouldNotGetRecordById404() throws Exception {
        Long id = 1L;
        given(recordService.getRecordById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetRecordById500() throws Exception {
        Long id = 1L;
        given(recordService.getRecordById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveRecord() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.saveRecord(any())).willReturn(record);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(record.getId().toString()))))
                .andExpect(jsonPath("$.success", is(record.isSuccess())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(record.getQuestionId().toString()))))
                .andExpect(jsonPath("$.progressId", is(Integer.valueOf(record.getProgressId().toString()))));
    }

    @Test
    public void shouldNotSaveRecord400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveRecord404Progress() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(progressDAO.findById(any())).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveRecord404Question() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(questionDAO.findById(any())).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveRecord409() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.getRecordByProgressIdAndQuestionId(any(), any())).willReturn(record);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldNotSaveRecord500() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.saveRecord(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldUpdateRecordById() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.updateRecordById(any(), eq(id))).willReturn(record);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/records/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(record.getId().toString()))))
                .andExpect(jsonPath("$.success", is(record.isSuccess())))
                .andExpect(jsonPath("$.questionId", is(Integer.valueOf(record.getQuestionId().toString()))))
                .andExpect(jsonPath("$.progressId", is(Integer.valueOf(record.getProgressId().toString()))));
    }

    @Test
    public void shouldNotUpdateRecordById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/records/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateRecordById404() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/records/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateRecordById500() throws Exception {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;
        RecordDTO record = new RecordDTO(id, isSuccess, questionId, progressId);

        given(recordService.updateRecordById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/records/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(record)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldDeleteRecordById() throws Exception {
        Long id = 1L;
        given(recordService.deleteRecordById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteRecordById404() throws Exception {
        Long id = 1L;
        given(recordService.deleteRecordById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteRecordById500() throws Exception {
        Long id = 1L;
        given(recordService.deleteRecordById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/records/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
