package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.entity.Quiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ProgressDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForProgressDTO() {
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 5;
        Intern intern = new Intern();
        intern.setId(2L);
        Quiz quiz = new Quiz();
        quiz.setId(3L);
        Long[] recordsIds = {4L, 3L};

        ProgressDTO progressDTO = new ProgressDTO();
        progressDTO.setId(id);
        progressDTO.setDateAndTimeOfCompletion(dateAndTimeOfCompletion);
        progressDTO.setScore(score);
        progressDTO.setInternId(intern.getId());
        progressDTO.setQuizId(quiz.getId());
        progressDTO.setRecordsIds(recordsIds);

        Assertions.assertEquals(id, progressDTO.getId());
        Assertions.assertEquals(dateAndTimeOfCompletion, progressDTO.getDateAndTimeOfCompletion());
        Assertions.assertEquals(score, progressDTO.getScore());
        Assertions.assertEquals(intern.getId(), progressDTO.getInternId());
        Assertions.assertEquals(quiz.getId(), progressDTO.getQuizId());
        Assertions.assertEquals(recordsIds, progressDTO.getRecordsIds());
    }

}
