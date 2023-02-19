package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.dto.RecordDTO;
import com.azerty.quizgame.model.Record;
import com.azerty.quizgame.utils.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImplementation implements RecordService {

    @Autowired
    private RecordDAO recordDAO;

    private final RecordMapper recordMapper = new RecordMapper();


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
        if (record.isPresent()) {
            return recordMapper.toRecordDTO(record.get());
        } else {
            return null;
        }
    }

    @Override
    public RecordDTO saveRecord(RecordDTO record) {
        return recordMapper.toRecordDTO(recordDAO.save(recordMapper.toRecord(record)));
    }

    @Override
    public RecordDTO updateRecordById(RecordDTO record, Long id) {
        Optional<Record> checkRecord = recordDAO.findById(id);
        if (checkRecord.isPresent()) {
            Record recordAsModel = recordMapper.toRecord(record);
            recordAsModel.setId(id);
            return recordMapper.toRecordDTO(recordDAO.save(recordAsModel));
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

}
