package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
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
    @Autowired
    private QuestionDAO questionDAO;


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
            Answer answerAsEntity = answerMapper.toAnswer(answer);
            answerAsEntity.setId(id);
            return answerMapper.toAnswerDTO(answerDAO.save(answerAsEntity));
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
        Optional<Question> checkQuestion = questionDAO.findById(questionId);
        if (checkQuestion.isPresent()) {

            Iterator<Answer> answerIterator = answerDAO.findAllAnswersByQuestionId(questionId).iterator();
            List<AnswerDTO> answers = new ArrayList<>();
            while (answerIterator.hasNext()) {
                answers.add(answerMapper.toAnswerDTO(answerIterator.next()));
            }
            return answers;
        } else {
            return null;
        }
    }

}
