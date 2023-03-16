package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgressTests {

    @Test
    public void shouldInstantiateProgressNoArgConstructor() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());

        Progress progress = new Progress();
        progress.setId(id);
        progress.setDateAndTimeOfCompletion(dateAndTimeOfCompletion);
        progress.setScore(score);
        progress.setPerson(person);
        progress.setQuiz(quiz);
        progress.setRecords(records);

        Assertions.assertEquals(id, progress.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progress.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progress.getScore());
        Assertions.assertEquals(person, progress.getPerson());
        Assertions.assertEquals(quiz, progress.getQuiz());
        Assertions.assertEquals(records, progress.getRecords());
        Assertions.assertEquals("Progress{" +
                "id=" + id +
                ", dateAndTimeOfCompletion=" + dateAndTimeOfCompletion +
                ", score=" + score +
                ", person=" + person +
                ", quiz=" + quiz +
                ", records=" + records +
                '}', progress.toString());
    }

    @Test
    public void shouldInstantiateProgressAllArgsConstructor() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());

        Progress progress = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);

        Assertions.assertEquals(null, progress.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progress.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progress.getScore());
        Assertions.assertEquals(person, progress.getPerson());
        Assertions.assertEquals(quiz, progress.getQuiz());
        Assertions.assertEquals(new ArrayList<>(), progress.getRecords());
        Assertions.assertEquals("Progress{" +
                "id=" + null +
                ", dateAndTimeOfCompletion=" + dateAndTimeOfCompletion +
                ", score=" + score +
                ", person=" + person +
                ", quiz=" + quiz +
                ", records=" + new ArrayList<>() +
                '}', progress.toString());
    }

}
