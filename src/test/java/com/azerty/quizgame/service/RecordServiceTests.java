package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.dto.RecordWithPickedAnswersDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.utils.RecordMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RecordServiceTests {

    @InjectMocks
    private RecordServiceImplementation recordService;
    @Mock
    private RecordDAO recordDAO;
    @Mock
    private ProgressDAO progressDAO;
    @Mock
    private QuestionDAO questionDAO;
    @Mock
    private AnswerDAO answerDAO;

    private final RecordMapper recordMapper = new RecordMapper();


    @Test
    public void shouldGetAllRecords() {
        // Given
        Long id1 = 1L;
        boolean isSuccess1 = true;
        Question question1 = new Question();
        Progress progress1 = new Progress();
        Record record1 = new Record(id1, isSuccess1, question1, progress1);
        Long id2 = 2L;
        boolean isSuccess2 = true;
        Question question2 = new Question();
        Progress progress2 = new Progress();
        Record record2 = new Record(id2, isSuccess2, question2, progress2);
        List<Record> recordsToReturn = new ArrayList<>();
        recordsToReturn.add(record1);
        recordsToReturn.add(record2);
        Mockito.when(recordDAO.findAll()).thenReturn(recordsToReturn);

        // When
        List<RecordDTO> records = recordService.getAllRecords();

        // Then
        assertEquals(recordsToReturn.size(), records.size());
    }

    @Test
    public void shouldNotGetAllRecords() {
        // Given
        Mockito.when(recordDAO.findAll()).thenReturn(null);

        // When
        List<RecordDTO> records = recordService.getAllRecords();

        // Then
        assertNull(records);
    }

    @Test
    public void shouldGetRecordById() {
        // Given
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Progress progress = new Progress();
        Record recordToReturn = new Record(id, isSuccess, question, progress);
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.of(recordToReturn));

        // When
        RecordDTO record = recordService.getRecordById(id);

        // Then
        assertEquals(recordToReturn.getId(), record.getId());
        assertEquals(recordToReturn.isSuccess(), record.isSuccess());
        Assertions.assertEquals(recordToReturn.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(recordToReturn.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldNotGetRecordById() {
        // Given
        Long id = 1L;
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.getRecordById(id);

        // Then
        assertNull(record);
    }

    @Test
    public void shouldSaveRecord() {
        // Given
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        Record recordToReturn = new Record(id, isSuccess, question, progress);
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progress));
        Mockito.when(recordDAO.save(any())).thenReturn(recordToReturn);

        // When
        RecordDTO record = recordService.saveRecord(recordMapper.toRecordDTO(recordToReturn));

        // Then
        assertEquals(recordToReturn.getId(), record.getId());
        assertEquals(recordToReturn.isSuccess(), record.isSuccess());
        Assertions.assertEquals(recordToReturn.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(recordToReturn.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldNotSaveRecord() {
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        Record recordToReturn = new Record(id, isSuccess, question, progress);
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.empty());
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.saveRecord(recordMapper.toRecordDTO(recordToReturn));

        // Then
        assertNull(record);
    }

    @Test
    public void shouldDeleteRecordById() {
        // Given
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Progress progress = new Progress();
        Record recordToReturn = new Record(id, isSuccess, question, progress);
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.of(recordToReturn));

        // When
        boolean isDeleted = recordService.deleteRecordById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeleteRecordById() {
        // Given
        Long id = 1L;
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = recordService.deleteRecordById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdateRecordById() {
        // Given
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        Record recordToReturn = new Record(id, isSuccess, question, progress);
        boolean isSuccess2 = false;
        Record updatedRecord = new Record(id, isSuccess2, question, progress);
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.of(recordToReturn));
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progress));
        Mockito.when(recordDAO.save(any())).thenReturn(updatedRecord);

        // When
        RecordDTO record = recordService.updateRecordById(recordMapper.toRecordDTO(updatedRecord), id);

        // Then
        assertEquals(updatedRecord.getId(), record.getId());
        assertEquals(updatedRecord.isSuccess(), record.isSuccess());
        Assertions.assertEquals(updatedRecord.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(updatedRecord.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldNotUpdateRecordById() {
        // Given
        Long id = 1L;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        boolean isSuccess2 = false;
        Record updatedRecord = new Record(id, isSuccess2, question, progress);
        Mockito.when(recordDAO.findById(id)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.updateRecordById(recordMapper.toRecordDTO(updatedRecord), id);

        // Then
        assertNull(record);
    }

    @Test
    public void shouldGetRecordByProgressIdAndQuestionId() {
        // Given
        Long progressId = 1L;
        Long questionId = 2L;
        Long recordId = 3L;
        boolean isSuccess = true;
        Question question = new Question();
        question.setId(questionId);
        Progress progress = new Progress();
        progress.setId(progressId);
        Record recordToReturn = new Record(recordId, isSuccess, question, progress);
        Mockito.when(recordDAO.findRecordByProgressIdAndQuestionId(progressId, questionId)).thenReturn(Optional.of(recordToReturn));

        // When
        RecordDTO record = recordService.getRecordByProgressIdAndQuestionId(progressId, questionId);

        // Then
        assertEquals(recordToReturn.getId(), record.getId());
        assertEquals(recordToReturn.isSuccess(), record.isSuccess());
        Assertions.assertEquals(recordToReturn.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(recordToReturn.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldNotGetRecordByProgressIdAndQuestionId() {
        // Given
        Long progressId = 1L;
        Long questionId = 2L;
        Mockito.when(recordDAO.findRecordByProgressIdAndQuestionId(progressId, questionId)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.getRecordByProgressIdAndQuestionId(progressId, questionId);

        // Then
        assertNull(record);
    }


    @Test
    public void shouldSaveRecordAndCheckAnswersSuccess() {
        // Given
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        Answer answer1 = new Answer();
        Long answerId1 = 4L;
        answer1.setId(answerId1);
        answer1.setCorrect(true);
        Answer answer2 = new Answer();
        Long answerId2 = 5L;
        answer2.setId(answerId2);
        answer2.setCorrect(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        List<Long> answersIds = new ArrayList<>(List.of(answerId1, answerId2));
        Long[] pickedAnswersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        RecordWithPickedAnswersDTO recordWithPickedAnswers = new RecordWithPickedAnswersDTO(questionId, progressId, pickedAnswersIds);
        Record recordToReturn = new Record(id, isSuccess, question, progress);

        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progress));
        Mockito.when(answerDAO.findCorrectAnswersIdsByQuestionId(questionId)).thenReturn(answersIds);
        Mockito.when(recordDAO.save(any())).thenReturn(recordToReturn);

        // When
        RecordDTO record = recordService.saveRecordAndCheckAnswers(recordWithPickedAnswers);

        // Then
        assertEquals(recordToReturn.getId(), record.getId());
        assertEquals(recordToReturn.isSuccess(), record.isSuccess());
        Assertions.assertEquals(recordToReturn.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(recordToReturn.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldSaveRecordAndCheckAnswersNotSuccess() {
        // Given
        Long id = 1L;
        boolean isSuccess = false;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Progress progress = new Progress();
        Long progressId = 3L;
        progress.setId(progressId);
        Answer answer1 = new Answer();
        Long answerId1 = 4L;
        answer1.setId(answerId1);
        answer1.setCorrect(false);
        Answer answer2 = new Answer();
        Long answerId2 = 5L;
        answer2.setId(answerId2);
        answer2.setCorrect(false);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        List<Long> answersIds = new ArrayList<>(List.of(answerId1, answerId2));
        Long[] pickedAnswersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        RecordWithPickedAnswersDTO recordWithPickedAnswers = new RecordWithPickedAnswersDTO(questionId, progressId, pickedAnswersIds);
        Record recordToReturn = new Record(id, isSuccess, question, progress);

        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progress));
        Mockito.when(answerDAO.findCorrectAnswersIdsByQuestionId(questionId)).thenReturn(new ArrayList<>());
        Mockito.when(recordDAO.save(any())).thenReturn(recordToReturn);

        // When
        RecordDTO record = recordService.saveRecordAndCheckAnswers(recordWithPickedAnswers);

        // Then
        assertEquals(recordToReturn.getId(), record.getId());
        assertEquals(recordToReturn.isSuccess(), record.isSuccess());
        Assertions.assertEquals(recordToReturn.getQuestion().getId(), record.getQuestionId());
        Assertions.assertEquals(recordToReturn.getProgress().getId(), record.getProgressId());
    }

    @Test
    public void shouldNotSaveRecordAndCheckAnswersQuestion() {
        // Given
        Long questionId = 2L;
        Long progressId = 3L;
        Answer answer1 = new Answer();
        Long answerId1 = 4L;
        answer1.setId(answerId1);
        answer1.setCorrect(true);
        Answer answer2 = new Answer();
        Long answerId2 = 5L;
        answer2.setId(answerId2);
        answer2.setCorrect(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Long[] pickedAnswersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        RecordWithPickedAnswersDTO recordWithPickedAnswers = new RecordWithPickedAnswersDTO(questionId, progressId, pickedAnswersIds);

        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.saveRecordAndCheckAnswers(recordWithPickedAnswers);

        // Then
        assertNull(record);
    }

    @Test
    public void shouldNotSaveRecordAndCheckAnswersProgress() {
        // Given
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Long progressId = 3L;
        Answer answer1 = new Answer();
        Long answerId1 = 4L;
        answer1.setId(answerId1);
        answer1.setCorrect(true);
        Answer answer2 = new Answer();
        Long answerId2 = 5L;
        answer2.setId(answerId2);
        answer2.setCorrect(true);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Long[] pickedAnswersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        RecordWithPickedAnswersDTO recordWithPickedAnswers = new RecordWithPickedAnswersDTO(questionId, progressId, pickedAnswersIds);

        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.empty());

        // When
        RecordDTO record = recordService.saveRecordAndCheckAnswers(recordWithPickedAnswers);

        // Then
        assertNull(record);
    }

}
