package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.RecordDTO;
import com.azerty.quizgame.model.Progress;
import com.azerty.quizgame.model.Question;
import com.azerty.quizgame.model.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {

    public RecordDTO toRecordDTO(Record record) {
        Long id = record.getId();
        int timeTakenInSeconds = record.getTimeTakenInSeconds();
        boolean isSuccess = record.isSuccess();
        Long questionId = record.getQuestion().getId();
        Long progressId = record.getProgress().getId();

        return new RecordDTO(id, timeTakenInSeconds, isSuccess, questionId, progressId);
    }

    public Record toRecord(RecordDTO recordDTO) {
        Question question = new Question();
        question.setId(recordDTO.getQuestionId());
        Progress progress = new Progress();
        progress.setId(recordDTO.getProgressId());

        return new Record(recordDTO.getId(), recordDTO.getTimeTakenInSeconds(), recordDTO.isSuccess(), question, progress);
    }

}
