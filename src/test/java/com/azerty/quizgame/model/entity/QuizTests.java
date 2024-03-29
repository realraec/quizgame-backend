package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuizTests {

    @Test
    public void shouldInstantiateQuizNoArgConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        persons.add(new Person());

        Quiz quiz = new Quiz();
        quiz.setId(id);
        quiz.setTitle(title);
        quiz.setSummary(summary);
        quiz.setQuestions(questions);
        quiz.setPersons(persons);

        Assertions.assertEquals(id, quiz.getId());
        Assertions.assertEquals(title, quiz.getTitle());
        Assertions.assertEquals(summary, quiz.getSummary());
        Assertions.assertEquals(questions, quiz.getQuestions());
        Assertions.assertEquals(persons, quiz.getPersons());
        Assertions.assertEquals("Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                //", questions=" + questions +
                //", persons=" + persons +
                '}', quiz.toString());
    }

    @Test
    public void shouldInstantiateQuizAllArgsConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        persons.add(new Person());

        Quiz quiz = new Quiz(id, title, summary, questions, persons);

        Assertions.assertEquals(null, quiz.getId());
        Assertions.assertEquals(title, quiz.getTitle());
        Assertions.assertEquals(summary, quiz.getSummary());
        Assertions.assertEquals(questions, quiz.getQuestions());
        Assertions.assertEquals(persons, quiz.getPersons());
        Assertions.assertEquals("Quiz{" +
                "id=" + null +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                //", questions=" + questions +
                //", persons=" + persons +
                '}', quiz.toString());
    }

}
