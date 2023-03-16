package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionInQuizDTOTests {

    @Test
    public void shouldInstantiateQuestionInQuizDTONoArgConstructor() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        AnswerInQuizDTO[] answers = {new AnswerInQuizDTO(), new AnswerInQuizDTO()};
        Long numberOfQuestionsLeft = 3L;

        QuestionInQuizDTO questionInQuizDTO = new QuestionInQuizDTO();
        questionInQuizDTO.setId(id);
        questionInQuizDTO.setWording(wording);
        questionInQuizDTO.setMaxDurationInSeconds(maxDurationInSeconds);
        questionInQuizDTO.setAnswers(answers);
        questionInQuizDTO.setNumberOfQuestionsLeft(numberOfQuestionsLeft);

        Assertions.assertEquals(id, questionInQuizDTO.getId());
        Assertions.assertEquals(wording, questionInQuizDTO.getWording());
        Assertions.assertEquals(maxDurationInSeconds, questionInQuizDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(numberOfQuestionsLeft, questionInQuizDTO.getNumberOfQuestionsLeft());
        Assertions.assertEquals(answers, questionInQuizDTO.getAnswers());
    }

    @Test
    public void shouldInstantiateQuestionInQuizDTOAllArgsConstructor() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        AnswerInQuizDTO[] answers = {new AnswerInQuizDTO(), new AnswerInQuizDTO()};
        Long numberOfQuestionsLeft = 3L;

        QuestionInQuizDTO questionInQuizDTO = new QuestionInQuizDTO(id, wording, maxDurationInSeconds, answers, numberOfQuestionsLeft);

        Assertions.assertEquals(id, questionInQuizDTO.getId());
        Assertions.assertEquals(wording, questionInQuizDTO.getWording());
        Assertions.assertEquals(maxDurationInSeconds, questionInQuizDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(numberOfQuestionsLeft, questionInQuizDTO.getNumberOfQuestionsLeft());
        Assertions.assertEquals(answers, questionInQuizDTO.getAnswers());
    }

}
