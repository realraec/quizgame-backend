package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordMapperTests {

    private final RecordMapper recordMapper = new RecordMapper();

    @Test
    public void shouldGoFromRecordToRecordDTO() {
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        question.setId(2L);
        Progress progress = new Progress();
        progress.setId(3L);

        Record record = new Record(id, isSuccess, question, progress);
        RecordDTO recordDTO = recordMapper.toRecordDTO(record);

        Assertions.assertEquals(null, recordDTO.getId());
        Assertions.assertEquals(isSuccess, recordDTO.isSuccess());
        Assertions.assertEquals(question.getId(), recordDTO.getQuestionId());
        Assertions.assertEquals(progress.getId(), recordDTO.getProgressId());
    }

    @Test
    public void shouldGoFromRecordDTOToRecord() {
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        question.setId(2L);
        Progress progress = new Progress();
        progress.setId(3L);

        RecordDTO recordDTO = new RecordDTO(id, isSuccess, question.getId(), progress.getId());
        Record record = recordMapper.toRecord(recordDTO);

        Assertions.assertEquals(null, record.getId());
        Assertions.assertEquals(isSuccess, record.isSuccess());
        Assertions.assertEquals(question.getId(), record.getQuestion().getId());
        Assertions.assertEquals(progress.getId(), record.getProgress().getId());
    }

}
