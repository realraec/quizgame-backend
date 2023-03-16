package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionTests {

    @Test
    public void shouldGetAndSetAllAttributesForQuestion() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer());
        answers.add(new Answer());

        Question question = new Question();
        question.setId(id);
        question.setWording(wording);
        question.setMaxDurationInSeconds(maxDurationInSeconds);
        question.setQuiz(quiz);
        question.setAnswers(answers);

        Assertions.assertEquals(id, question.getId());
        Assertions.assertEquals(wording, question.getWording());
        Assertions.assertEquals(maxDurationInSeconds, question.getMaxDurationInSeconds());
        Assertions.assertEquals(quiz, question.getQuiz());
        Assertions.assertEquals(answers, question.getAnswers());
        Assertions.assertEquals("Question{" +
                "id=" + id +
                ", wording='" + wording + '\'' +
                ", maxDurationInSeconds=" + maxDurationInSeconds +
                ", quiz=" + quiz +
                ", answers=" + answers +
                '}', question.toString());
    }

}
