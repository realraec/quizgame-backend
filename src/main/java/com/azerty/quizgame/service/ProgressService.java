package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.dto.RecordWithPickedAnswersDTO;

import java.util.List;

public interface ProgressService {

    List<ProgressDTO> getAllProgresses() throws Exception;

    ProgressDTO getProgressById(Long id) throws Exception;

    ProgressDTO saveProgress(ProgressDTO progress) throws Exception;

    boolean deleteProgressById(Long id) throws Exception;

    ProgressDTO updateProgressById(ProgressDTO progress, Long id) throws Exception;

    List<ProgressDTO> getAllProgressesByPersonId(Long personId) throws Exception;

    ProgressDTO getProgressByPersonIdAndQuizId(Long personId, Long quizId) throws Exception;

    boolean updateProgressDependingOnRecord(RecordDTO record) throws Exception;

}
