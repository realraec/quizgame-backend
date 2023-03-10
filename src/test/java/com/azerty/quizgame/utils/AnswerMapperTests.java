package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.dto.AnswerInQuizDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerMapperTests {

    private final AnswerMapper answerMapper = new AnswerMapper();


    @Test
    public void shouldGoFromAnswerToAnswerInQuizDTO() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);

        Answer answer = new Answer(id, wording, isCorrect, question);
        AnswerInQuizDTO answerInQuizDTO = answerMapper.toAnswerInQuizDTO(answer);

        Assertions.assertEquals(null, answerInQuizDTO.getId());
        Assertions.assertEquals(wording, answerInQuizDTO.getWording());
        Assertions.assertEquals(isCorrect, answerInQuizDTO.isCorrect());
    }

    @Test
    public void shouldGoFromAnswerToAnswerDTO() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);

        Answer answer = new Answer(id, wording, isCorrect, question);
        AnswerDTO answerDTO = answerMapper.toAnswerDTO(answer);

        Assertions.assertEquals(null, answerDTO.getId());
        Assertions.assertEquals(wording, answerDTO.getWording());
        Assertions.assertEquals(isCorrect, answerDTO.isCorrect());
        Assertions.assertEquals(question.getId(), answerDTO.getQuestionId());
    }

    @Test
    public void shouldGoFromAnswerDTOToAnswer() {
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);

        AnswerDTO answerDTO = new AnswerDTO(id, wording, isCorrect, question.getId());
        Answer answer = answerMapper.toAnswer(answerDTO);

        Assertions.assertEquals(null, answer.getId());
        Assertions.assertEquals(wording, answer.getWording());
        Assertions.assertEquals(isCorrect, answer.isCorrect());
        Assertions.assertEquals(question.getId(), answer.getQuestion().getId());
    }

}
