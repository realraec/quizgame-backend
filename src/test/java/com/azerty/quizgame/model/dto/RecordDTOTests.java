package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordDTOTests {

    @Test
    public void shouldInstantiateRecordDTONoArgConstructor() {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;

        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setId(id);
        recordDTO.setSuccess(isSuccess);
        recordDTO.setQuestionId(questionId);
        recordDTO.setProgressId(progressId);

        Assertions.assertEquals(id, recordDTO.getId());
        Assertions.assertEquals(isSuccess, recordDTO.isSuccess());
        Assertions.assertEquals(questionId, recordDTO.getQuestionId());
        Assertions.assertEquals(progressId, recordDTO.getProgressId());
    }

    @Test
    public void shouldInstantiateRecordDTOAllArgsConstructor() {
        Long id = 1L;
        boolean isSuccess = true;
        Long questionId = 2L;
        Long progressId = 3L;

        RecordDTO recordDTO = new RecordDTO(id, isSuccess, questionId, progressId);

        Assertions.assertEquals(id, recordDTO.getId());
        Assertions.assertEquals(isSuccess, recordDTO.isSuccess());
        Assertions.assertEquals(questionId, recordDTO.getQuestionId());
        Assertions.assertEquals(progressId, recordDTO.getProgressId());
    }

}
