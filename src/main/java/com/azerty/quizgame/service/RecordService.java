package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.dto.RecordWithPickedAnswersDTO;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords() throws Exception;

    RecordDTO getRecordById(Long id) throws Exception;

    RecordDTO saveRecord(RecordDTO quiz) throws Exception;

    boolean deleteRecordById(Long id) throws Exception;

    RecordDTO updateRecordById(RecordDTO quiz, Long id) throws Exception;

    RecordDTO getRecordByProgressIdAndQuestionId(Long progressId, Long questionId) throws Exception;

    RecordDTO saveRecordAndCheckAnswers(RecordWithPickedAnswersDTO record) throws Exception;

}
