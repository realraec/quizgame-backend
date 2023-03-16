package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgressTests {

    @Test
    public void shouldGetAndSetAllAttributesForProgress() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Intern intern = new Intern();
        Quiz quiz = new Quiz();
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());

        Progress progress = new Progress();
        progress.setId(id);
        progress.setDateAndTimeOfCompletion(dateAndTimeOfCompletion);
        progress.setIntern(intern);
        progress.setScore(score);
        progress.setQuiz(quiz);
        progress.setRecords(records);

        Assertions.assertEquals(id, progress.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progress.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progress.getScore());
        Assertions.assertEquals(intern, progress.getIntern());
        Assertions.assertEquals(quiz, progress.getQuiz());
        Assertions.assertEquals(records, progress.getRecords());
        Assertions.assertEquals("Progress{" +
                "id=" + id +
                ", dateAndTimeOfCompletion=" + dateAndTimeOfCompletion +
                ", score=" + score +
                ", intern=" + intern +
                ", quiz=" + quiz +
                ", records=" + records +
                '}', progress.toString());
    }

}
