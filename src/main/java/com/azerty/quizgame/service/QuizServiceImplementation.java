package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.InternDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.entity.QuizState;
import com.azerty.quizgame.utils.QuizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImplementation implements QuizService {

    @Autowired
    private QuizDAO quizDAO;
    @Autowired
    private ProgressDAO progressDAO;
    @Autowired
    private InternDAO internDAO;

    private final QuizMapper quizMapper = new QuizMapper();


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
        if (quiz.isPresent()) {
            return quizMapper.toQuizDTO(quiz.get());
        } else {
            return null;
        }
    }

    @Override
    public QuizDTO saveQuiz(QuizDTO quiz) {
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
