package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.entity.Record;
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

        //Then
        assertEquals(progressesToReturn.size(), progresses.size());
    }

    @Test
    public void shouldNotGetAllProgresses() {
        // Given
        Mockito.when(progressDAO.findAll()).thenReturn(null);

        // When
        List<ProgressDTO> progresses = progressService.getAllProgresses();

        //Then
        assertEquals(null, progresses);
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

        //Then
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

        //Then
        assertEquals(null, progress);
    }

}
