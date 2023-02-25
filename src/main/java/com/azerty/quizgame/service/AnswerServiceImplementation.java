package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.utils.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImplementation implements AnswerService {

    @Autowired
    private AnswerDAO answerDAO;

    private final AnswerMapper answerMapper = new AnswerMapper();


    @Override
    public List<AnswerDTO> getAllAnswers() {
        Iterator<Answer> answerIterator = answerDAO.findAll().iterator();
        List<AnswerDTO> answers = new ArrayList<>();
        while (answerIterator.hasNext()) {
            answers.add(answerMapper.toAnswerDTO(answerIterator.next()));
        }

        if (!answers.isEmpty()) {
            return answers;
        } else {
            return null;
        }
    }

    @Override
    public AnswerDTO getAnswerById(Long id) {
        Optional<Answer> answer = answerDAO.findById(id);
        if (answer.isPresent()) {
            return answerMapper.toAnswerDTO(answer.get());
        } else {
            return null;
        }
    }

    @Override
    public AnswerDTO saveAnswer(AnswerDTO answer) {
        return answerMapper.toAnswerDTO(answerDAO.save(answerMapper.toAnswer(answer)));
    }

    @Override
    public AnswerDTO updateAnswerById(AnswerDTO answer, Long id) {
        Optional<Answer> checkAnswer = answerDAO.findById(id);
        if (checkAnswer.isPresent()) {
            Answer answerAsModel = answerMapper.toAnswer(answer);
            answerAsModel.setId(id);
            return answerMapper.toAnswerDTO(answerDAO.save(answerAsModel));
        } else {
            return null;
        }
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
    public List<AnswerDTO> getAllAnswersByQuestionId(Long questionId) {
        Iterator<Answer> answerIterator = answerDAO.findAllAnswersByQuestionId(questionId).iterator();
        List<AnswerDTO> answers = new ArrayList<>();
        while (answerIterator.hasNext()) {
            answers.add(answerMapper.toAnswerDTO(answerIterator.next()));
        }

        if (!answers.isEmpty()) {
            return answers;
        } else {
            return null;
        }
    }

//    @Override
//    public List<AnswerDTO> getAllAnswersByQuizId(Long id) {
//        Iterator<Answer> answerIterator = answerDAO.findAllAnswersByQuizId(id).iterator();
//        List<AnswerDTO> answers = new ArrayList<>();
//
//        while (answerIterator.hasNext()) {
//            answers.add(answerMapper.toAnswerDTO(answerIterator.next()));
//        }
//
//        if (!answers.isEmpty()) {
//            return answers;
//        } else {
//            return null;
//        }
//    }

}
