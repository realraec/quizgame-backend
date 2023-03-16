package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerInQuizDTOTests {

    @Test
    public void shouldInstantiateAnswerInQuizDTONoArgConstructor() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;

        AnswerInQuizDTO answerInQuizDTO = new AnswerInQuizDTO();
        answerInQuizDTO.setId(id);
        answerInQuizDTO.setWording(wording);
        answerInQuizDTO.setCorrect(isCorrect);

        Assertions.assertEquals(id, answerInQuizDTO.getId());
        Assertions.assertEquals(wording, answerInQuizDTO.getWording());
        Assertions.assertEquals(isCorrect, answerInQuizDTO.isCorrect());
    }

    @Test
    public void shouldInstantiateAnswerInQuizDTO() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;

        AnswerInQuizDTO answerInQuizDTO = new AnswerInQuizDTO(id, wording, isCorrect);

        Assertions.assertEquals(id, answerInQuizDTO.getId());
        Assertions.assertEquals(wording, answerInQuizDTO.getWording());
        Assertions.assertEquals(isCorrect, answerInQuizDTO.isCorrect());
    }

}
