package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.RecordDTO;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords() throws Exception;

    RecordDTO getRecordById(Long id) throws Exception;

    RecordDTO saveRecord(RecordDTO quiz) throws Exception;

    boolean deleteRecordById(Long id) throws Exception;

    RecordDTO updateRecordById(RecordDTO quiz, Long id) throws Exception;

    RecordDTO getRecordByProgressIdAndQuestionId(Long personId, Long questionId) throws Exception;

}
