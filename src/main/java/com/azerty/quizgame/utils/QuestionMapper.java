package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.AnswerInQuizDTO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionInQuizDTO toQuestionInQuizDTO(List<Object[]> objectArraysList) {
        if (objectArraysList.isEmpty()) {
            return new QuestionInQuizDTO();
        }

        Object[] firstObject = objectArraysList.get(0);
        Long questionId = (Long) firstObject[0];
        String questionWording = (String) firstObject[1];
        Integer questionMaxDurationInSeconds = (Integer) firstObject[2];
        Long numberOfQuestionsLeft = (Long) firstObject[6];

        List<Object> objectsAsList = new ArrayList<>();
        for (int i = 0; i < objectArraysList.size(); i++) {
            Object[] currentObject = objectArraysList.get(i);

            Long answerId = (Long) currentObject[3];
            boolean answerIsCorrect = (Boolean) currentObject[4];
            String answerWording = (String) currentObject[5];

            AnswerInQuizDTO answerInQuizDTO = new AnswerInQuizDTO(answerId, answerWording, answerIsCorrect);
            objectsAsList.add(answerInQuizDTO);
        }
        AnswerInQuizDTO[] answersInQuizDTO = objectsAsList.toArray(AnswerInQuizDTO[]::new);

        QuestionInQuizDTO questionInQuizDTO = new QuestionInQuizDTO(questionId, questionWording, questionMaxDurationInSeconds, answersInQuizDTO, numberOfQuestionsLeft);
        return questionInQuizDTO;
    }

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
