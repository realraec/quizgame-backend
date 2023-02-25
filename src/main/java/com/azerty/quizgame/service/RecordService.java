package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.RecordDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords() throws Exception;

    RecordDTO getRecordById(Long id) throws Exception;

    @Transactional
    RecordDTO saveRecord(RecordDTO quiz) throws Exception;

    boolean deleteRecordById(Long id) throws Exception;

    RecordDTO updateRecordById(RecordDTO quiz, Long id) throws Exception;

}
