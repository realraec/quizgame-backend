package com.azerty.quizgame.service;

import com.azerty.quizgame.dto.RecordDTO;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords() throws Exception;

    RecordDTO getRecordById(Long id) throws Exception;

    RecordDTO saveRecord(RecordDTO quiz) throws Exception;

    boolean deleteRecordById(Long id) throws Exception;

    RecordDTO updateRecordById(RecordDTO quiz, Long id) throws Exception;

}
