package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.QuestionDTO;
import com.azerty.quizgame.model.Answer;
import com.azerty.quizgame.model.Question;
import com.azerty.quizgame.model.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionDTO toQuestionDTO(Question question) {
        Long id = question.getId();
        String wording = question.getWording();
        int maxDurationInSeconds = question.getMaxDurationInSeconds();
        Long quizId = question.getQuiz().getId();
        Long[] answersIds = question.getAnswers().stream().map(Answer::getId).toArray(Long[]::new);

        return new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);
    }

    public Question toQuestion(QuestionDTO questionDTO) {
        Quiz quiz = new Quiz();
        quiz.setId(questionDTO.getQuizId());
        List<Answer> answers = new ArrayList<>();
        Long[] answersIds = questionDTO.getAnswersIds();
        for (int i = 0; i < answersIds.length; i++) {
            Answer answer = new Answer();
            answer.setId(answersIds[i]);
            answers.add(answer);
        }

        return new Question(questionDTO.getId(), questionDTO.getWording(), questionDTO.getMaxDurationInSeconds(), quiz, answers);
    }

}
