package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.dto.RecordWithPickedAnswersDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {

    public RecordDTO toRecordDTO(Record record) {
        Long id = record.getId();
        boolean isSuccess = record.isSuccess();
        Long questionId = record.getQuestion().getId();
        Long progressId = record.getProgress().getId();

        return new RecordDTO(id, isSuccess, questionId, progressId);
    }

    public Record toRecord(RecordDTO recordDTO) {
        Question question = new Question();
        question.setId(recordDTO.getQuestionId());
        Progress progress = new Progress();
        progress.setId(recordDTO.getProgressId());

        return new Record(recordDTO.getId(), recordDTO.isSuccess(), question, progress);
    }

    public Record toRecord(RecordWithPickedAnswersDTO recordDTO, boolean isSuccess) {
        Question question = new Question();
        question.setId(recordDTO.getQuestionId());
        Progress progress = new Progress();
        progress.setId(recordDTO.getProgressId());

        return new Record(null, isSuccess, question, progress);
    }

}
