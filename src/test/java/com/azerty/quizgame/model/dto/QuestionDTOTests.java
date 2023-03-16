package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.entity.Quiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionDTOTests {

    @Test
    public void shouldInstantiateQuestionDTONoArgConstructor() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long[] answersIds = {3L, 4L};

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setWording(wording);
        questionDTO.setMaxDurationInSeconds(maxDurationInSeconds);
        questionDTO.setQuizId(quiz.getId());
        questionDTO.setAnswersIds(answersIds);

        Assertions.assertEquals(id, questionDTO.getId());
        Assertions.assertEquals(wording, questionDTO.getWording());
        Assertions.assertEquals(maxDurationInSeconds, questionDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(quiz.getId(), questionDTO.getQuizId());
        Assertions.assertEquals(answersIds, questionDTO.getAnswersIds());
    }

    @Test
    public void shouldInstantiateQuestionDTOAllArgsConstructor() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long[] answersIds = {3L, 4L};

        QuestionDTO questionDTO = new QuestionDTO(id, wording, maxDurationInSeconds, quiz.getId(), answersIds);

        Assertions.assertEquals(id, questionDTO.getId());
        Assertions.assertEquals(wording, questionDTO.getWording());
        Assertions.assertEquals(maxDurationInSeconds, questionDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(quiz.getId(), questionDTO.getQuizId());
        Assertions.assertEquals(answersIds, questionDTO.getAnswersIds());
    }

}
