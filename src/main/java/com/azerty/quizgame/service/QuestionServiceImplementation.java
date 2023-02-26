package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
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
    @Autowired
    private ProgressDAO progressDAO;
    @Autowired
    private QuizDAO quizDAO;

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
            Question questionAsEntity = questionMapper.toQuestion(question);
            questionAsEntity.setId(id);
            return questionMapper.toQuestionDTO(questionDAO.save(questionAsEntity));
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

    @Override
    public List<QuestionDTO> getAllQuestionsByQuizId(Long quizId) {
        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        if (checkQuiz.isPresent()) {

            Iterator<Question> questionIterator = questionDAO.findAllQuestionsByQuizId(quizId).iterator();
            List<QuestionDTO> questions = new ArrayList<>();
            while (questionIterator.hasNext()) {
                questions.add(questionMapper.toQuestionDTO(questionIterator.next()));
            }
            return questions;
        } else {
            return null;
        }
    }

    @Override
    public QuestionDTO getSingleQuestionInQuizWithIdNotInProgressRecordsByProgressId(Long progressId) {
        Optional<Progress> checkProgress = progressDAO.findById(progressId);
        if (checkProgress.isPresent()) {

            List<Question> questions = questionDAO.findAllQuestionsInQuizWithIdNotInProgressRecordsByProgressId(progressId);
            if (questions.size() > 0) {
                Question question = questions.get(0);
                return questionMapper.toQuestionDTO(question);
            } else {
                return new QuestionDTO();
            }
        } else {
            return null;
        }
    }

}
