package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.AnswerDTO;
import com.azerty.quizgame.model.Answer;
import com.azerty.quizgame.model.Question;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public AnswerDTO toAnswerDTO(Answer answer) {
        Long id = answer.getId();
        String wording = answer.getWording();
        boolean isCorrect = answer.isCorrect();
        Long questionId = answer.getQuestion().getId();

        return new AnswerDTO(id, wording, isCorrect, questionId);
    }

    public Answer toAnswer(AnswerDTO answerDTO) {
        Question question = new Question();
        question.setId(answerDTO.getQuestionId());

        return new Answer(answerDTO.getId(), answerDTO.getWording(), answerDTO.isCorrect(), question);
    }

}
