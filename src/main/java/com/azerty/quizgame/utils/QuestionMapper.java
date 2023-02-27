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

    private final AnswerMapper answerMapper = new AnswerMapper();

    public QuestionInQuizDTO toQuestionInQuizDTO(List<Question> questions) {
        Question question = questions.get(0);
        Long id = question.getId();
        String wording = question.getWording();
        int maxDurationInSeconds = question.getMaxDurationInSeconds();

        List<AnswerInQuizDTO> answersAsList = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            Answer answer = questions.get(i).getAnswers().get(0);
            answersAsList.add(answerMapper.toAnswerInQuizDTO(answer));
        }
        AnswerInQuizDTO[] answers = answersAsList.toArray(AnswerInQuizDTO[]::new);

        return new QuestionInQuizDTO(id, wording, maxDurationInSeconds, answers);
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
