package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.model.entity.*;
import com.azerty.quizgame.utils.ProgressMapper;
import com.azerty.quizgame.utils.RecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ProgressServiceTests {

    @InjectMocks
    private ProgressServiceImplementation progressService;
    @Mock
    private ProgressDAO progressDAO;
    @Mock
    private PersonDAO personDAO;
    @Mock
    private QuizDAO quizDAO;
    @Mock
    private RecordDAO recordDAO;

    private final ProgressMapper progressMapper = new ProgressMapper();
    private final RecordMapper recordMapper = new RecordMapper();


    @Test
    public void shouldGetAllProgresses() {
        // Given
        Long id1 = 1L;
        LocalDateTime dateAndTimeOfCompletion1 = LocalDateTime.now();
        int score1 = 3;
        Person person1 = new Person();
        Quiz quiz1 = new Quiz();
        List<Record> records1 = new ArrayList<>();
        records1.add(new com.azerty.quizgame.model.entity.Record());
        records1.add(new Record());
        Progress progress1 = new Progress(id1, dateAndTimeOfCompletion1, score1, person1, quiz1, records1);

        Long id2 = 2L;
        LocalDateTime dateAndTimeOfCompletion2 = LocalDateTime.now();
        int score2 = 4;
        Person person2 = new Person();
        Quiz quiz2 = new Quiz();
        List<Record> records2 = new ArrayList<>();
        records2.add(new Record());
        records2.add(new Record());
        Progress progress2 = new Progress(id2, dateAndTimeOfCompletion2, score2, person2, quiz2, records2);

        List<Progress> progressesToReturn = new ArrayList<>();
        progressesToReturn.add(progress1);
        progressesToReturn.add(progress2);
        Mockito.when(progressDAO.findAll()).thenReturn(progressesToReturn);

        // When
        List<ProgressDTO> progresses = progressService.getAllProgresses();

        // Then
        assertEquals(progressesToReturn.size(), progresses.size());
    }

    @Test
    public void shouldNotGetAllProgresses() {
        // Given
        Mockito.when(progressDAO.findAll()).thenReturn(null);

        // When
        List<ProgressDTO> progresses = progressService.getAllProgresses();

        // Then
        assertNull(progresses);
    }

    @Test
    public void shouldGetProgressById() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.of(progressToReturn));

        // When
        ProgressDTO progress = progressService.getProgressById(id);

        // Then
        assertEquals(progressToReturn.getId(), progress.getId());
        assertEquals(progressToReturn.getDateAndTimeOfCompletion(), progress.getDateAndTimeOfCompletion());
        assertEquals(progressToReturn.getScore(), progress.getScore());
        assertEquals(progressToReturn.getQuiz().getId(), progress.getQuizId());
        assertEquals(recordsIds.length, progress.getRecordsIds().length);
    }

    @Test
    public void shouldNotGetProgressById() {
        // Given
        Long id = 1L;
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.empty());

        // When
        ProgressDTO progress = progressService.getProgressById(id);

        // Then
        assertNull(progress);
    }

    @Test
    public void shouldSaveProgress() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        Record record1 = new Record();
        Long recordId1 = 4L;
        record1.setId(recordId1);
        Record record2 = new Record();
        Long recordId2 = 5L;
        record2.setId(recordId2);
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(recordDAO.findById(recordId1)).thenReturn(Optional.of(record1));
        Mockito.when(recordDAO.findById(recordId2)).thenReturn(Optional.of(record2));
        Mockito.when(progressDAO.save(any())).thenReturn(progressToReturn);

        // When
        ProgressDTO progress = progressService.saveProgress(progressMapper.toProgressDTO(progressToReturn));

        // Then
        assertEquals(progressToReturn.getId(), progress.getId());
        assertEquals(progressToReturn.getDateAndTimeOfCompletion(), progress.getDateAndTimeOfCompletion());
        assertEquals(progressToReturn.getScore(), progress.getScore());
        assertEquals(progressToReturn.getQuiz().getId(), progress.getQuizId());
        assertEquals(recordsIds.length, progress.getRecordsIds().length);
    }

    @Test
    public void shouldSaveProgressNoRecords() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        List<Record> records = new ArrayList<>();
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(progressDAO.save(any())).thenReturn(progressToReturn);

        // When
        ProgressDTO progress = progressService.saveProgress(progressMapper.toProgressDTO(progressToReturn));

        // Then
        assertEquals(progressToReturn.getId(), progress.getId());
        assertEquals(progressToReturn.getDateAndTimeOfCompletion(), progress.getDateAndTimeOfCompletion());
        assertEquals(progressToReturn.getScore(), progress.getScore());
        assertEquals(progressToReturn.getQuiz().getId(), progress.getQuizId());
        assertEquals(recordsIds.length, progress.getRecordsIds().length);
    }

    @Test
    public void shouldNotSaveProgressRecord() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        Record record1 = new Record();
        Long recordId1 = 4L;
        record1.setId(recordId1);
        Record record2 = new Record();
        Long recordId2 = 5L;
        record2.setId(recordId2);
        List<Record> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(recordDAO.findById(recordId1)).thenReturn(Optional.of(record1));

        // When
        ProgressDTO progress = progressService.saveProgress(progressMapper.toProgressDTO(progressToReturn));

        // Then
        assertNull(progress);
    }

    @Test
    public void shouldNotSaveProgressPersonQuiz() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        List<Record> records = new ArrayList<>();
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.empty());
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        ProgressDTO progress = progressService.saveProgress(progressMapper.toProgressDTO(progressToReturn));

        // Then
        assertNull(progress);
    }

    @Test
    public void shouldDeleteProgressById() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Quiz quiz = new Quiz();
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.of(progressToReturn));

        // When
        boolean isDeleted = progressService.deleteProgressById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeleteProgressById() {
        // Given
        Long id = 1L;
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = progressService.deleteProgressById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdateProgressById() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        List<Record> records = new ArrayList<>();
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        int score2 = 5;
        Progress updatedProgress = new Progress(id, dateAndTimeOfCompletion, score2, person, quiz, records);
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.of(progressToReturn));
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(progressDAO.save(any())).thenReturn(updatedProgress);

        // When
        ProgressDTO progress = progressService.updateProgressById(progressMapper.toProgressDTO(updatedProgress), id);

        // Then
        assertEquals(updatedProgress.getId(), progress.getId());
        assertEquals(updatedProgress.getDateAndTimeOfCompletion(), progress.getDateAndTimeOfCompletion());
        assertEquals(updatedProgress.getScore(), progress.getScore());
        assertEquals(updatedProgress.getQuiz().getId(), progress.getQuizId());
        assertEquals(recordsIds.length, progress.getRecordsIds().length);
    }

    @Test
    public void shouldNotUpdateProgressById() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        List<Record> records = new ArrayList<>();
        records.add(new Record());
        records.add(new Record());
        int score2 = 5;
        Progress updatedProgress = new Progress(id, dateAndTimeOfCompletion, score2, person, quiz, records);
        Mockito.when(progressDAO.findById(id)).thenReturn(Optional.empty());

        // When
        ProgressDTO progress = progressService.updateProgressById(progressMapper.toProgressDTO(updatedProgress), id);

        // Then
        assertNull(progress);
    }

    @Test
    public void shouldGetAllProgressesByPersonId() {
        // Given
        Person person = new Person();
        Long personId = 5L;
        person.setId(personId);

        Long id1 = 1L;
        LocalDateTime dateAndTimeOfCompletion1 = LocalDateTime.now();
        int score1 = 3;
        Quiz quiz1 = new Quiz();
        List<Record> records1 = new ArrayList<>();
        records1.add(new com.azerty.quizgame.model.entity.Record());
        records1.add(new Record());
        Progress progress1 = new Progress(id1, dateAndTimeOfCompletion1, score1, person, quiz1, records1);

        Long id2 = 2L;
        LocalDateTime dateAndTimeOfCompletion2 = LocalDateTime.now();
        int score2 = 4;
        Quiz quiz2 = new Quiz();
        List<Record> records2 = new ArrayList<>();
        records2.add(new Record());
        records2.add(new Record());
        Progress progress2 = new Progress(id2, dateAndTimeOfCompletion2, score2, person, quiz2, records2);

        List<Progress> progressesToReturn = new ArrayList<>();
        progressesToReturn.add(progress1);
        progressesToReturn.add(progress2);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(progressDAO.findAllProgressesByPersonId(personId)).thenReturn(progressesToReturn);

        // When
        List<ProgressDTO> progresses = progressService.getAllProgressesByPersonId(personId);

        // Then
        assertEquals(progressesToReturn.size(), progresses.size());
    }

    @Test
    public void shouldNotGetAllProgressesByPersonId() {
        // Given
        Long personId = 1L;
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.empty());

        // When
        List<ProgressDTO> progresses = progressService.getAllProgressesByPersonId(personId);

        // Then
        assertNull(progresses);
    }

    /*@Test
    public void shouldNotGetAllProgressesByPersonId() {
        // Given
        Long personId = 1L;
        Mockito.when(progressDAO.findAllProgressesByPersonId(personId)).thenReturn(null);

        // When
        List<ProgressDTO> progresses = progressService.getAllProgressesByPersonId(personId);

        // Then
        assertNull(progresses);
    }*/

    @Test
    public void shouldGetProgressByPersonIdAndQuizId() {
        // Given
        Long id = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        List<Record> records = new ArrayList<>();
        Long[] recordsIds = records.stream().map(Record::getId).toArray(Long[]::new);
        Progress progressToReturn = new Progress(id, dateAndTimeOfCompletion, score, person, quiz, records);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(progressDAO.findProgressByPersonIdAndQuizId(personId, quizId)).thenReturn(Optional.of(progressToReturn));

        // When
        ProgressDTO progress = progressService.getProgressByPersonIdAndQuizId(personId, quizId);

        // Then
        assertEquals(progressToReturn.getId(), progress.getId());
        assertEquals(progressToReturn.getDateAndTimeOfCompletion(), progress.getDateAndTimeOfCompletion());
        assertEquals(progressToReturn.getScore(), progress.getScore());
        assertEquals(progressToReturn.getQuiz().getId(), progress.getQuizId());
        assertEquals(recordsIds.length, progress.getRecordsIds().length);
    }

    @Test
    public void shouldNotGetProgressByPersonIdAndQuizIdPersonQuiz() {
        // Given
        Long personId = 2L;
        Long quizId = 3L;
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.empty());
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        ProgressDTO progress = progressService.getProgressByPersonIdAndQuizId(personId, quizId);

        // Then
        assertNull(progress);
    }

    @Test
    public void shouldNotGetProgressByPersonIdAndQuizIdProgress() {
        // Given
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(progressDAO.findProgressByPersonIdAndQuizId(personId, quizId)).thenReturn(Optional.empty());

        // When
        ProgressDTO progress = progressService.getProgressByPersonIdAndQuizId(personId, quizId);

        // Then
        assertNull(progress);
    }


    @Test
    public void shouldUpdateProgressDependingOnRecord() {
        // Given
        Long progressId = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;

        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);
        Record record = new Record();
        Long recordId = 4L;
        record.setId(recordId);
        boolean isSuccess = true;
        record.setSuccess(isSuccess);
        Question question = new Question();
        Long questionId = 5L;
        question.setId(questionId);

        List<Record> records = new ArrayList<>();
        records.add(record);
        Progress progressToReturn = new Progress(progressId, dateAndTimeOfCompletion, score, person, quiz, records);
        progressToReturn.setId(progressId);

        record.setQuestion(question);
        record.setProgress(progressToReturn);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        quiz.setQuestions(questions);
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progressToReturn));
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(recordDAO.findById(recordId)).thenReturn(Optional.of(record));
        Mockito.when(progressDAO.save(any())).thenReturn(progressToReturn);

        // When
        boolean isUpdated = progressService.updateProgressDependingOnRecord(recordMapper.toRecordDTO(record));

        // Then
        assertTrue(isUpdated);
        // Then
    }

    @Test
    public void shouldNotUpdateProgressDependingOnRecord() {
        // Given
        Long progressId = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        Person person = new Person();
        Long personId = 2L;
        person.setId(personId);
        Quiz quiz = new Quiz();
        Long quizId = 3L;
        quiz.setId(quizId);

        Record record = new Record();
        Long recordId = 4L;
        record.setId(recordId);
        Question question = new Question();
        Long questionId = 5L;
        question.setId(questionId);
        record.setQuestion(question);
        List<Record> records = new ArrayList<>();
        records.add(record);
        Progress progressToReturn = new Progress(progressId, dateAndTimeOfCompletion, score, person, quiz, records);
        progressToReturn.setId(progressId);

        record.setProgress(progressToReturn);
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.empty());

        // When
        boolean isUpdated = progressService.updateProgressDependingOnRecord(recordMapper.toRecordDTO(record));

        // Then
        assertFalse(isUpdated);
    }

}
