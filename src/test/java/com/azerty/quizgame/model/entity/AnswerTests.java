package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTests {

    @Test
    public void shouldInstantiateAnswerNoArgConstructor() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();

        Answer answer = new Answer();
        answer.setId(id);
        answer.setWording(wording);
        answer.setCorrect(isCorrect);
        answer.setQuestion(question);

        Assertions.assertEquals(id, answer.getId());
        Assertions.assertEquals(wording, answer.getWording());
        Assertions.assertEquals(isCorrect, answer.isCorrect());
        Assertions.assertEquals(question, answer.getQuestion());
        Assertions.assertEquals("Answer{" +
                "id=" + id +
                ", wording='" + wording + '\'' +
                ", isCorrect=" + isCorrect +
                ", question=" + question +
                '}', answer.toString());
    }

    @Test
    public void shouldInstantiateAnswerAllArgsConstructor() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();

        Answer answer = new Answer(id, wording, isCorrect, question);

        Assertions.assertEquals(null, answer.getId());
        Assertions.assertEquals(wording, answer.getWording());
        Assertions.assertEquals(isCorrect, answer.isCorrect());
        Assertions.assertEquals(question, answer.getQuestion());
        Assertions.assertEquals("Answer{" +
                "id=" + null +
                ", wording='" + wording + '\'' +
                ", isCorrect=" + isCorrect +
                ", question=" + question +
                '}', answer.toString());
    }

}
