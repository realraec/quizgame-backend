package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.enums.QuizState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuizForInternDTOTests {

    @Test
    public void shouldInstantiateQuizForInternDTONoArgConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        QuizState state = QuizState.STARTED;
        Long progressId = 2L;

        QuizForInternDTO quizDTO = new QuizForInternDTO();
        quizDTO.setId(id);
        quizDTO.setTitle(title);
        quizDTO.setSummary(summary);
        quizDTO.setState(state);
        quizDTO.setProgressId(progressId);

        Assertions.assertEquals(id, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(state, quizDTO.getState());
        Assertions.assertEquals(progressId, quizDTO.getProgressId());
    }

    @Test
    public void shouldInstantiateQuizForInternDTOAllArgsConstructor() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        QuizState state = QuizState.STARTED;
        Long personId = 2L;

        QuizForInternDTO quizDTO = new QuizForInternDTO(id, title, summary, state, personId);

        Assertions.assertEquals(id, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(state, quizDTO.getState());
        Assertions.assertEquals(personId, quizDTO.getProgressId());
    }

}
