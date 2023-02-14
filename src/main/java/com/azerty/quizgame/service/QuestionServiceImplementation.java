package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = (List<Question>) questionDAO.findAll();

        if (!questions.isEmpty()) {
            return questions;
        } else {
            return null;
        }
    }

    @Override
    public Question getQuestionById(Long id) {
        Optional<Question> question = questionDAO.findById(id);
        return question.orElse(null);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionDAO.save(question);
    }

    @Override
    public boolean deleteQuestionById(Long id) {
        Optional<Question> checkQuestion = questionDAO.findById(id);
        if (checkQuestion.isPresent()) {
            questionDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Question updateQuestionById(Question question, Long id) {
        Optional<Question> checkQuestion = questionDAO.findById(id);
        if (checkQuestion.isPresent()) {
            question.setId(id);
            return questionDAO.save(question);
        } else {
            return null;
        }
    }

}
