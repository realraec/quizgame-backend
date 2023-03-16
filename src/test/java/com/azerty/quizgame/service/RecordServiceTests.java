package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Record;
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

        //Then
        assertEquals(recordsToReturn.size(), records.size());
    }

    @Test
    public void shouldNotGetAllRecords() {
        // Given
        Mockito.when(recordDAO.findAll()).thenReturn(null);

        // When
        List<RecordDTO> records = recordService.getAllRecords();

        //Then
        assertEquals(null, records);
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

        //Then
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

        //Then
        assertEquals(null, record);
    }

}
