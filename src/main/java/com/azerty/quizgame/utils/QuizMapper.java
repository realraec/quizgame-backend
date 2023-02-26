package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.entity.QuizState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizMapper {

    public QuizDTO toQuizDTO(Quiz quiz) {
        Long id = quiz.getId();
        String title = quiz.getTitle();
        String summary = quiz.getSummary();
        Long[] questionsIds = quiz.getQuestions().stream().map(Question::getId).toArray(Long[]::new);

        return new QuizDTO(id, title, summary, questionsIds);
    }

    public QuizForInternDTO toQuizForInternDTO(Quiz quiz, QuizState quizState) {
        Long id = quiz.getId();
        String title = quiz.getTitle();
        String summary = quiz.getSummary();

        return new QuizForInternDTO(id, title, summary, quizState);
    }

    public Quiz toQuiz(QuizDTO quizDTO) {
        List<Question> questions = new ArrayList<>();
        Long[] questionsIds = quizDTO.getQuestionsIds();
        for (int i = 0; i < questionsIds.length; i++) {
            Question question = new Question();
            question.setId(questionsIds[i]);
            questions.add(question);
        }

        return new Quiz(quizDTO.getId(), quizDTO.getTitle(), quizDTO.getSummary(), questions);
    }

}
