package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordWithPickedAnswersDTOTests {

    @Test
    public void shouldInstantiateRecordWithPickedAnswersDTONoArgConstructor() {
        Long questionId = 2L;
        Long progressId = 3L;
        Long[] pickedAnswersIds = {4L, 5L};

        RecordWithPickedAnswersDTO recordDTO = new RecordWithPickedAnswersDTO();
        recordDTO.setQuestionId(questionId);
        recordDTO.setProgressId(progressId);
        recordDTO.setPickedAnswersIds(pickedAnswersIds);

        Assertions.assertEquals(questionId, recordDTO.getQuestionId());
        Assertions.assertEquals(progressId, recordDTO.getProgressId());
        Assertions.assertEquals(pickedAnswersIds, recordDTO.getPickedAnswersIds());
    }

    @Test
    public void shouldInstantiateRecordWithPickedAnswersDTOAllArgsConstructor() {
        Long questionId = 2L;
        Long progressId = 3L;
        Long[] pickedAnswersIds = {4L, 5L};

        RecordWithPickedAnswersDTO recordDTO = new RecordWithPickedAnswersDTO(questionId, progressId, pickedAnswersIds);

        Assertions.assertEquals(questionId, recordDTO.getQuestionId());
        Assertions.assertEquals(progressId, recordDTO.getProgressId());
        Assertions.assertEquals(pickedAnswersIds, recordDTO.getPickedAnswersIds());
    }

}
