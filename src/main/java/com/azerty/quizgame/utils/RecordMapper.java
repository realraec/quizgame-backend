package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.RecordDTO;
import com.azerty.quizgame.model.Question;
import com.azerty.quizgame.model.Record;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecordMapper {

    public RecordDTO toRecordDTO(Record record) {
        Long id = record.getId();
        Long questionId = record.getQuestion().getId();
        boolean isSuccess = record.isSuccess();

        return new RecordDTO(id, questionId, isSuccess);
    }

    public Record toRecord(RecordDTO recordDTO) {
        Question question = new Question();
        question.setId(recordDTO.getQuestionId());

        return new Record(recordDTO.getId(), question, recordDTO.isSuccess());
    }

}
