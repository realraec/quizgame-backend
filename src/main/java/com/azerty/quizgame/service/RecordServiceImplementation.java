package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.utils.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImplementation implements RecordService {

    private final RecordDAO recordDAO;
    private final ProgressDAO progressDAO;
    private final QuestionDAO questionDAO;
    private final RecordMapper recordMapper = new RecordMapper();

    @Autowired
    public RecordServiceImplementation(RecordDAO recordDAO,
                                       ProgressDAO progressDAO,
                                       QuestionDAO questionDAO) {
        this.recordDAO = recordDAO;
        this.progressDAO = progressDAO;
        this.questionDAO = questionDAO;
    }


    @Override
    public List<RecordDTO> getAllRecords() {
        Iterator<Record> recordIterator = recordDAO.findAll().iterator();
        List<RecordDTO> records = new ArrayList<>();
        while (recordIterator.hasNext()) {
            records.add(recordMapper.toRecordDTO(recordIterator.next()));
        }

        if (!records.isEmpty()) {
            return records;
        } else {
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
            return recordMapper.toRecordDTO(recordDAO.save(recordMapper.toRecord(record)));
        } else {
            return null;
        }
    }

    @Override
    public RecordDTO updateRecordById(RecordDTO record, Long id) {
        Optional<Record> checkRecord = recordDAO.findById(id);
        if (checkRecord.isPresent()) {
            Record recordAsEntity = recordMapper.toRecord(record);
            recordAsEntity.setId(id);
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


    public RecordDTO getRecordByProgressIdAndQuestionId(Long personId, Long questionId) {
        Optional<Record> record = recordDAO.findRecordByProgressIdAndQuestionId(personId, questionId);
        return record.map(recordMapper::toRecordDTO).orElse(null);
    }


}
