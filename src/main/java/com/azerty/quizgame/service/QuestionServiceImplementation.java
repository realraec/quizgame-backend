package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dto.QuestionDTO;
import com.azerty.quizgame.model.Question;
import com.azerty.quizgame.utils.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    private final QuestionMapper questionMapper = new QuestionMapper();


    @Override
    public List<QuestionDTO> getAllQuestions() {
        Iterator<Question> questionIterator = questionDAO.findAll().iterator();
        List<QuestionDTO> questions = new ArrayList<>();

        while (questionIterator.hasNext()) {
            questions.add(questionMapper.toQuestionDTO(questionIterator.next()));
        }

        if (!questions.isEmpty()) {
            return questions;
        } else {
            return null;
        }
    }

    @Override
    public QuestionDTO getQuestionById(Long id) {
        Optional<Question> question = questionDAO.findById(id);
        if (question.isPresent()) {
            return questionMapper.toQuestionDTO(question.get());
        } else {
            return null;
        }
    }

    @Override
    public QuestionDTO saveQuestion(QuestionDTO question) {
        return questionMapper.toQuestionDTO(questionDAO.save(questionMapper.toQuestion(question)));
    }

    @Override
    public QuestionDTO updateQuestionById(QuestionDTO question, Long id) {
        Optional<Question> checkQuestion = questionDAO.findById(id);
        if (checkQuestion.isPresent()) {
            Question questionAsModel = questionMapper.toQuestion(question);
            questionAsModel.setId(id);
            return questionMapper.toQuestionDTO(questionDAO.save(questionAsModel));
        } else {
            return null;
        }
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

}
