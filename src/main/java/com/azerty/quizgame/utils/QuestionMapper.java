package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.AnswerInQuizDTO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    public QuestionInQuizDTO toQuestionInQuizDTO(List<Object[]> objectArraysList) {
        if (objectArraysList.isEmpty()) {
            return new QuestionInQuizDTO();
        }

        Object[] firstObject = objectArraysList.get(0);
        Long questionId = (Long) firstObject[0];
        Integer questionMaxDurationInSeconds = (Integer) firstObject[1];
        String questionWording = (String) firstObject[2];

        List<Object> objectsAsList = new ArrayList<>();
        for (int i = 0; i < objectArraysList.size(); i++) {
            Object[] currentObject = objectArraysList.get(i);

            Long answerId = (Long) currentObject[3];
            boolean answerIsCorrect = (Boolean) currentObject[4];
            String answerWording = (String) currentObject[5];

            AnswerInQuizDTO answerInQuizDTO = new AnswerInQuizDTO(answerId, answerIsCorrect, answerWording);
            objectsAsList.add(answerInQuizDTO);
        }
        AnswerInQuizDTO[] answersInQuizDTO = objectsAsList.toArray(AnswerInQuizDTO[]::new);

        QuestionInQuizDTO questionInQuizDTO = new QuestionInQuizDTO(questionId, questionMaxDurationInSeconds, questionWording, answersInQuizDTO);
        return questionInQuizDTO;
    }

/*    public QuestionTogetherWithAllItsAnswers[] toQuestionTogetherWithAllItsAnswersDTO(List<Object[]> objectArraysList) {
        List<QuestionTogetherWithAllItsAnswers> questionTogetherWithAllItsAnswersAsList = new ArrayList<>();

        for (int i = 0; i < objectArraysList.size(); i++) {
            Object[] currentObject = objectArraysList.get(i);

            Long questionId = (Long) currentObject[0];
            Integer questionMaxDurationInSeconds = (Integer) currentObject[1];
            String questionWording = (String) currentObject[2];

            Long answerId = (Long) currentObject[3];
            boolean answerIsCorrect = (Boolean) currentObject[4];
            String answerWording = (String) currentObject[5];

            QuestionTogetherWithAllItsAnswers questionTogetherWithAllItsAnswers = new QuestionTogetherWithAllItsAnswers(questionId, questionMaxDurationInSeconds, questionWording, answerId, answerIsCorrect, answerWording);
            questionTogetherWithAllItsAnswersAsList.add(questionTogetherWithAllItsAnswers);
        }

        QuestionTogetherWithAllItsAnswers[] questionTogetherWithAllItsAnswersAsArray = questionTogetherWithAllItsAnswersAsList.toArray(QuestionTogetherWithAllItsAnswers[]::new);
        return questionTogetherWithAllItsAnswersAsArray;
    }*/

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
