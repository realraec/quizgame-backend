package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.entity.Record;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgressMapper {

    public ProgressDTO toProgressDTO(Progress progress) {
        Long id = progress.getId();
        LocalDateTime dateAndTimeOfCompletion = progress.getDateAndTimeOfCompletion();
        int durationInSeconds = progress.getDurationInSeconds();
        int score = progress.getScore();
        Long internId = progress.getIntern().getId();
        Long quizId = progress.getQuiz().getId();
        Long[] records = progress.getRecords().stream().map(Record::getId).toArray(Long[]::new);

        return new ProgressDTO(id, dateAndTimeOfCompletion, durationInSeconds, score, internId, quizId, records);
    }

    public Progress toProgress(ProgressDTO progressDTO) {
        Intern intern = new Intern();
        intern.setId(progressDTO.getInternId());
        Quiz quiz = new Quiz();
        quiz.setId(progressDTO.getQuizId());
        List<Record> records = new ArrayList<>();
        Long[] recordsIds = progressDTO.getRecordsIds();
        for (int i = 0; i < recordsIds.length; i++) {
            Record record = new Record();
            record.setId(recordsIds[i]);
            records.add(record);
        }

        return new Progress(progressDTO.getId(), progressDTO.getDateAndTimeOfCompletion(), progressDTO.getDurationInSeconds(), progressDTO.getScore(), intern, quiz, records);
    }

}
