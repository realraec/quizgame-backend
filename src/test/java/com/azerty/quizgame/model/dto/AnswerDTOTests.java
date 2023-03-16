package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerDTOTests {

    @Test
    public void shouldInstantiateAnswerDTONoArgConstructor() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(id);
        answerDTO.setWording(wording);
        answerDTO.setCorrect(isCorrect);
        answerDTO.setQuestionId(question.getId());

        Assertions.assertEquals(id, answerDTO.getId());
        Assertions.assertEquals(wording, answerDTO.getWording());
        Assertions.assertEquals(isCorrect, answerDTO.isCorrect());
        Assertions.assertEquals(question.getId(), answerDTO.getQuestionId());
    }

    @Test
    public void shouldInstantiateAnswerDTOAllArgsConstructor() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);

        AnswerDTO answerDTO = new AnswerDTO(id, wording, isCorrect, question.getId());

        Assertions.assertEquals(id, answerDTO.getId());
        Assertions.assertEquals(wording, answerDTO.getWording());
        Assertions.assertEquals(isCorrect, answerDTO.isCorrect());
        Assertions.assertEquals(question.getId(), answerDTO.getQuestionId());
    }

}
