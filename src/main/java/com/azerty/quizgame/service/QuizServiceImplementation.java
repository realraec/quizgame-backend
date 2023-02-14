package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class QuizServiceImplementation implements QuizService {

    @Autowired
    private QuizDAO quizDAO;

    @Override
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = (List<Quiz>) quizDAO.findAll();

        if (!quizzes.isEmpty()) {
            return quizzes;
        } else {
            return null;
        }
    }

    @Override
    public Quiz getQuizById(Long id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        return quiz.orElse(null);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizDAO.save(quiz);
    }

    @Override
    public boolean deleteQuizById(Long id) {
        Optional<Quiz> checkQuiz = quizDAO.findById(id);
        if (checkQuiz.isPresent()) {
            quizDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Quiz updateQuizById(Quiz question, Long id) {
        Optional<Quiz> checkQuiz = quizDAO.findById(id);
        if (checkQuiz.isPresent()) {
            question.setId(id);
            return quizDAO.save(question);
        } else {
            return null;
        }
    }
}
