package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.dto.RecordWithPickedAnswersDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.utils.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecordServiceImplementation implements RecordService {

    private final RecordDAO recordDAO;
    private final ProgressDAO progressDAO;
    private final QuestionDAO questionDAO;
    private final RecordMapper recordMapper = new RecordMapper();
    private final AnswerDAO answerDAO;


    @Autowired
    public RecordServiceImplementation(RecordDAO recordDAO,
                                       ProgressDAO progressDAO,
                                       QuestionDAO questionDAO,
                                       AnswerDAO answerDAO) {
        this.recordDAO = recordDAO;
        this.progressDAO = progressDAO;
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
    }


    @Override
    public List<RecordDTO> getAllRecords() {
        try {
            Iterator<Record> recordIterator = recordDAO.findAll().iterator();
            List<RecordDTO> records = new ArrayList<>();
            while (recordIterator.hasNext()) {
                records.add(recordMapper.toRecordDTO(recordIterator.next()));
            }
            return records;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public RecordDTO getRecordById(Long id) {
        Optional<Record> record = recordDAO.findById(id);
        return record.map(recordMapper::toRecordDTO).orElse(null);
    }

    @Override
    public RecordDTO saveRecord(RecordDTO record) {
        Optional<Question> checkQuestion = questionDAO.findById(record.getQuestionId());
        Optional<Progress> checkProgress = progressDAO.findById(record.getProgressId());
        if (checkQuestion.isPresent() && checkProgress.isPresent()) {
            Record recordAsEntity = recordMapper.toRecord(record);
            recordAsEntity.setId(record.getId());
            return recordMapper.toRecordDTO(recordDAO.save(recordAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteRecordById(Long id) {
        Optional<Record> checkRecord = recordDAO.findById(id);
        if (checkRecord.isPresent()) {
            recordDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public RecordDTO updateRecordById(RecordDTO record, Long id) {
        Optional<Record> checkRecord = recordDAO.findById(id);
        if (checkRecord.isPresent()) {
            Record recordAsEntity = recordMapper.toRecord(record);
            recordAsEntity.setId(id);
            return saveRecord(recordMapper.toRecordDTO(recordAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public RecordDTO getRecordByProgressIdAndQuestionId(Long progressId, Long questionId) {
        Optional<Record> record = recordDAO.findRecordByProgressIdAndQuestionId(progressId, questionId);
        return record.map(recordMapper::toRecordDTO).orElse(null);
    }

    @Override
    public RecordDTO saveRecordAndCheckAnswers(RecordWithPickedAnswersDTO record) {
        Optional<Question> checkQuestion = questionDAO.findById(record.getQuestionId());
        Optional<Progress> checkProgress = progressDAO.findById(record.getProgressId());
        if (checkQuestion.isPresent() && checkProgress.isPresent()) {
            boolean isSuccess = true;

            Long[] answersIds = record.getPickedAnswersIds();
            List<Long> answersIdsAsList = Arrays.stream(answersIds).toList();
            List<Long> correctAnswersIds = answerDAO.findCorrectAnswersIdsByQuestionId(checkQuestion.get().getId());
            if (!answersIdsAsList.equals(correctAnswersIds)) {
                isSuccess = false;
            }

            Record recordAsEntity = recordMapper.toRecord(record, isSuccess);
            return recordMapper.toRecordDTO(recordDAO.save(recordAsEntity));
        } else {
            return null;
        }
    }

}
