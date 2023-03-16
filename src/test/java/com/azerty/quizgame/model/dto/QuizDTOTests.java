package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuizDTOTests {

    @Test
    public void shouldInstantiateQuizDTONoArgConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Long[] questionsIds = new Long[]{2L, 3L};
        Long[] personsIds = new Long[]{4L, 5L};

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(id);
        quizDTO.setTitle(title);
        quizDTO.setSummary(summary);
        quizDTO.setQuestionsIds(questionsIds);
        quizDTO.setPersonsIds(personsIds);

        Assertions.assertEquals(id, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(questionsIds, quizDTO.getQuestionsIds());
        Assertions.assertEquals(personsIds, quizDTO.getPersonsIds());
    }

    @Test
    public void shouldInstantiateQuizDTOAllArgsConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Long[] questionsIds = new Long[]{2L, 3L};
        Long[] personsIds = new Long[]{4L, 5L};

        QuizDTO quizDTO = new QuizDTO(id, title, summary, questionsIds, personsIds);
        quizDTO.setId(id);
        quizDTO.setTitle(title);
        quizDTO.setSummary(summary);
        quizDTO.setQuestionsIds(questionsIds);
        quizDTO.setPersonsIds(personsIds);

        Assertions.assertEquals(id, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(questionsIds, quizDTO.getQuestionsIds());
        Assertions.assertEquals(personsIds, quizDTO.getPersonsIds());
    }

}
