package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuizDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForQuizDTO() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Long[] questionsIds = new Long[]{2L, 3L};

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(id);
        quizDTO.setTitle(title);
        quizDTO.setSummary(summary);
        quizDTO.setQuestionsIds(questionsIds);

        Assertions.assertEquals(id, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(questionsIds, quizDTO.getQuestionsIds());
    }

}
