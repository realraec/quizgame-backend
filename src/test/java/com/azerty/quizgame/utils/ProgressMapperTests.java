package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.entity.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgressMapperTests {

    private final ProgressMapper progressMapper = new ProgressMapper();

    @Test
    public void shouldGoFromProgressToProgressDTO() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        Record record1 = new Record();
        record1.setId(2L);
        Record record2 = new Record();
        record2.setId(3L);
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        //Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);

        Progress progress = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, null);
        progress.setRecords(records);
        ProgressDTO progressDTO = progressMapper.toProgressDTO(progress);

        Assertions.assertEquals(null, progressDTO.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progressDTO.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progressDTO.getScore());
        Assertions.assertEquals(person.getId(), progressDTO.getPersonId());
        Assertions.assertEquals(quiz.getId(), progressDTO.getQuizId());
        Assertions.assertEquals(records.size(), progressDTO.getRecordsIds().length);
    }

    @Test
    public void shouldGoFromProgressDTOToProgress() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        Record record1 = new Record();
        record1.setId(2L);
        Record record2 = new Record();
        record2.setId(3L);
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);

        ProgressDTO progressDTO = new ProgressDTO(id, dateAndTimeOfCompletion, score, person.getId(), quiz.getId(), recordsIds);
        Progress progress = progressMapper.toProgress(progressDTO);

        Assertions.assertEquals(null, progress.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progress.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progress.getScore());
        Assertions.assertEquals(person.getId(), progress.getPerson().getId());
        Assertions.assertEquals(quiz.getId(), progress.getQuiz().getId());
        Assertions.assertEquals(records.size(), progress.getRecords().size());
    }

}
