package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dto.QuestionDTO;
import com.azerty.quizgame.model.Progress;
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

    @Autowired
    private ProgressDAO progressDAO;

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

    @Override
    public List<QuestionDTO> getAllQuestionsByQuizId(Long quizId) {
        Iterator<Question> questionIterator = questionDAO.findAllQuestionsByQuizId(quizId).iterator();
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
    public List<QuestionDTO> getAllQuestionsByQuizIdWithIdNotInArray(Long quizId, Long[] questionsIds) {
        Iterator<Question> questionIterator = questionDAO.findAllQuestionsByQuizIdWithIdNotInArray(quizId, questionsIds).iterator();
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
    public List<QuestionDTO> getAllQuestionsByInternIdAndQuizId(Long internId, Long quizId) {
        Iterator<Question> questionIterator = questionDAO.findAllQuestionsByInternIdAndQuizId(internId, quizId).iterator();
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
    public QuestionDTO getSingleQuestionInQuizWithIdNotInProgressRecordsByProgressId(Long progressId) {
        Optional<Progress> progress = progressDAO.findById(progressId);
        if (progress.isPresent()) {
            List<Question> questions = questionDAO.findAllQuestionsInQuizWithIdNotInProgressRecordsByProgressId(progressId);
            System.out.println(questions);

            if (questions.size() > 0) {
                Question question = questions.get(0);
                return questionMapper.toQuestionDTO(question);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
