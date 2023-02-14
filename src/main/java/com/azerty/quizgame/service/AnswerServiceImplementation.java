package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AnswerServiceImplementation implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public List<Answer> getAllAnswers() {
        List<Answer> answers = (List<Answer>) answerDAO.findAll();

        if (!answers.isEmpty()) {
            return answers;
        } else {
            return null;
        }
    }

    @Override
    public Answer getAnswerById(Long id) {
        Optional<Answer> answer = answerDAO.findById(id);
        return answer.orElse(null);
    }

    @Override
    public Answer saveAnswer(Answer answer) {
        return answerDAO.save(answer);
    }

    @Override
    public boolean deleteAnswerById(Long id) {
        Optional<Answer> checkAnswer = answerDAO.findById(id);
        if (checkAnswer.isPresent()) {
            answerDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Answer updateAnswerById(Answer answer, Long id) {
        Optional<Answer> checkAnswer = answerDAO.findById(id);
        if (checkAnswer.isPresent()) {
            answer.setId(id);
            return answerDAO.save(answer);
        } else {
            return null;
        }
    }

}
