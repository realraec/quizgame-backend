package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapperTests {

    private final QuestionMapper questionMapper = new QuestionMapper();


    @Test
    public void shouldGoFromObjectArraysListToQuestionInQuizDTO() {
        Object[] objectArray1 = new Object[]{6L, "What command is used to create a new table in SQL?", 30, 23L, true, "CREATE TABLE", 2L};
        Object[] objectArray2 = new Object[]{6L, "What command is used to create a new table in SQL?", 30, 24L, false, "SETUP TABLE", 2L};
        List<Object[]> objectArraysList = new ArrayList<>();
        objectArraysList.add(objectArray1);
        objectArraysList.add(objectArray2);

        QuestionInQuizDTO questionDTO = questionMapper.toQuestionInQuizDTO(objectArraysList);

        Assertions.assertEquals(objectArraysList.get(0)[0], questionDTO.getId());
        Assertions.assertEquals(objectArraysList.get(0)[1], questionDTO.getWording());
        Assertions.assertEquals(objectArraysList.get(0)[2], questionDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(objectArraysList.get(0)[3], questionDTO.getAnswers()[0].getId());
        Assertions.assertEquals(objectArraysList.get(0)[4], questionDTO.getAnswers()[0].isCorrect());
        Assertions.assertEquals(objectArraysList.get(0)[5], questionDTO.getAnswers()[0].getWording());
        Assertions.assertEquals(objectArraysList.get(1)[3], questionDTO.getAnswers()[1].getId());
        Assertions.assertEquals(objectArraysList.get(1)[4], questionDTO.getAnswers()[1].isCorrect());
        Assertions.assertEquals(objectArraysList.get(1)[5], questionDTO.getAnswers()[1].getWording());
        Assertions.assertEquals(objectArraysList.get(0)[6], questionDTO.getNumberOfQuestionsLeft());
    }

    @Test
    public void shouldGoFromObjectArraysListToQuestionInQuizDTOEmpty() {
        QuestionInQuizDTO questionDTO = questionMapper.toQuestionInQuizDTO(new ArrayList<>());

        Assertions.assertEquals(null, questionDTO.getId());
        Assertions.assertEquals(null, questionDTO.getWording());
        Assertions.assertEquals(0, questionDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(null, questionDTO.getAnswers());
        Assertions.assertEquals(null, questionDTO.getNumberOfQuestionsLeft());
    }

    @Test
    public void shouldGoFromQuestionToQuestionDTO() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        quiz.setId(2L);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer());
        answers.add(new Answer());
        //Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);

        Question question = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        question.setAnswers(answers);
        QuestionDTO questionDTO = questionMapper.toQuestionDTO(question);

        Assertions.assertEquals(null, questionDTO.getId());
        Assertions.assertEquals(wording, questionDTO.getWording());
        Assertions.assertEquals(maxDurationInSeconds, questionDTO.getMaxDurationInSeconds());
        Assertions.assertEquals(quiz.getId(), questionDTO.getQuizId());
        Assertions.assertEquals(answers.size(), questionDTO.getAnswersIds().length);
    }

    @Test
    public void shouldGoFromQuestionDTOToQuestion() {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        quiz.setId(2L);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer());
        answers.add(new Answer());
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);

        QuestionDTO questionDTO = new QuestionDTO(id, wording, maxDurationInSeconds, quiz.getId(), answersIds);
        Question question = questionMapper.toQuestion(questionDTO);

        Assertions.assertEquals(null, question.getId());
        Assertions.assertEquals(wording, question.getWording());
        Assertions.assertEquals(maxDurationInSeconds, question.getMaxDurationInSeconds());
        Assertions.assertEquals(quiz.getId(), question.getQuiz().getId());
        Assertions.assertEquals(answers.size(), question.getAnswers().size());
    }

}
