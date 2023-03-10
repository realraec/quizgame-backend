package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.InternDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.*;
import com.azerty.quizgame.model.enums.QuizState;
import com.azerty.quizgame.utils.QuizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImplementation implements QuizService {

    private final QuizDAO quizDAO;
    private final ProgressDAO progressDAO;
    private final InternDAO internDAO;
    private final QuizMapper quizMapper = new QuizMapper();
    private final QuestionDAO questionDAO;


    @Autowired
    public QuizServiceImplementation(QuizDAO quizDAO, ProgressDAO progressDAO, InternDAO internDAO,
                                     QuestionDAO questionDAO) {
        this.quizDAO = quizDAO;
        this.progressDAO = progressDAO;
        this.internDAO = internDAO;
        this.questionDAO = questionDAO;
    }


    @Override
    public List<QuizDTO> getAllQuizzes() {
        Iterator<Quiz> quizIterator = quizDAO.findAll().iterator();
        List<QuizDTO> quizzes = new ArrayList<>();
        while (quizIterator.hasNext()) {
            quizzes.add(quizMapper.toQuizDTO(quizIterator.next()));
        }

        if (!quizzes.isEmpty()) {
            return quizzes;
        } else {
            return null;
        }
    }

    @Override
    public QuizDTO getQuizById(Long id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        return quiz.map(quizMapper::toQuizDTO).orElse(null);
    }

    @Override
    public QuizDTO saveQuiz(QuizDTO quiz) {
        for (int i = 0; i < quiz.getQuestionsIds().length; i++) {
            Optional<Question> checkQuestion = questionDAO.findById(quiz.getQuestionsIds()[i]);
            if (checkQuestion.isEmpty()) {
                return null;
            }
        }
        return quizMapper.toQuizDTO(quizDAO.save(quizMapper.toQuiz(quiz)));
    }

    @Override
    public QuizDTO updateQuizById(QuizDTO quiz, Long id) {
        Optional<Quiz> checkQuiz = quizDAO.findById(id);
        if (checkQuiz.isPresent()) {
            Quiz quizAsEntity = quizMapper.toQuiz(quiz);
            quizAsEntity.setId(id);
            return quizMapper.toQuizDTO(quizDAO.save(quizAsEntity));
        } else {
            return null;
        }
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
    public QuizForInternDTO getQuizWithStateByQuizIdAndInternId(Long quizId, Long internId) {
        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        Optional<Intern> checkIntern = internDAO.findById(internId);
        if (checkQuiz.isPresent() && checkIntern.isPresent()) {

            QuizState quizState = QuizState.NOT_STARTED;
            Progress progress = progressDAO.findProgressByInternIdAndQuizId(internId, quizId);
            if (progress != null) {
                quizState = QuizState.STARTED;
                if (progress.getDateAndTimeOfCompletion() != null) {
                    quizState = QuizState.COMPLETED;
                }
            }
            return quizMapper.toQuizForInternDTO(checkQuiz.get(), quizState);
        } else {
            return null;
        }
    }

    @Override
    public List<QuizForInternDTO> getAllQuizzesWithStateByInternId(Long internId) {
        Optional<Intern> checkIntern = internDAO.findById(internId);
        if (checkIntern.isPresent()) {

            Iterator<Quiz> quizIterator = quizDAO.findAll().iterator();
            List<QuizForInternDTO> quizzesForIntern = new ArrayList<>();
            while (quizIterator.hasNext()) {
                Quiz quiz = quizIterator.next();
                QuizState quizState = QuizState.NOT_STARTED;
                Progress progress = progressDAO.findProgressByInternIdAndQuizId(internId, quiz.getId());
                if (progress != null) {
                    quizState = QuizState.STARTED;
                    if (progress.getDateAndTimeOfCompletion() != null) {
                        quizState = QuizState.COMPLETED;
                    }
                }
                quizzesForIntern.add(quizMapper.toQuizForInternDTO(quiz, quizState));
            }
            return quizzesForIntern;
        } else {
            return null;
        }
    }

}
