package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForRecordDTO() {
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

}
