package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.JourneyDTO;
import com.azerty.quizgame.model.Answer;
import com.azerty.quizgame.model.Intern;
import com.azerty.quizgame.model.Journey;
import com.azerty.quizgame.model.Quiz;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JourneyMapper {

    public JourneyDTO toJourneyDTO(Journey journey) {
        Long id = journey.getId();
        LocalDateTime dateAndTimeOfCompletion = journey.getDateAndTimeOfCompletion();
        int durationInSeconds = journey.getDurationInSeconds();
        int score = journey.getScore();
        Long internId = journey.getIntern().getId();
        Long quizId = journey.getQuiz().getId();
        Long[] answersIds = journey.getAnswers().stream().map(Answer::getId).toArray(Long[]::new);

        return new JourneyDTO(id, dateAndTimeOfCompletion, durationInSeconds, score, internId, quizId, answersIds);
    }

    public Journey toJourney(JourneyDTO journeyDTO) {
        Intern intern = new Intern();
        intern.setId(journeyDTO.getInternId());
        Quiz quiz = new Quiz();
        quiz.setId(journeyDTO.getQuizId());
        List<Answer> answers = new ArrayList<>();
        Long[] answersIds = journeyDTO.getAnswersIds();
        for (int i = 0; i < answersIds.length; i++) {
            Answer answer = new Answer();
            answer.setId(answersIds[i]);
            answers.add(answer);
        }

        return new Journey(journeyDTO.getId(), journeyDTO.getDateAndTimeOfCompletion(), journeyDTO.getDurationInSeconds(), journeyDTO.getScore(), intern, quiz, answers);
    }

}
