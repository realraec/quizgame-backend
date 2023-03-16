package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AnswerServiceTests {

    @InjectMocks
    private AnswerServiceImplementation answerService;
    @Mock
    private AnswerDAO answerDAO;
    @Mock
    private QuestionDAO questionDAO;

    @Test
    public void shouldGetAllAnswers() {
        // Given
        Long id1 = 1L;
        String wording1 = "The number 42.";
        boolean isCorrect1 = true;
        Question question1 = new Question();
        Answer answer1 = new Answer(id1, wording1, isCorrect1, question1);
        Long id2 = 2L;
        String wording2 = "Any other number.";
        boolean isCorrect2 = false;
        Question question2 = new Question();
        Answer answer2 = new Answer(id2, wording2, isCorrect2, question2);
        List<Answer> answersToReturn = new ArrayList<>();
        answersToReturn.add(answer1);
        answersToReturn.add(answer2);
        Mockito.when(answerDAO.findAll()).thenReturn(answersToReturn);

        // When
        List<AnswerDTO> answers = answerService.getAllAnswers();

        //Then
        assertEquals(answersToReturn.size(), answers.size());
    }

    @Test
    public void shouldNotGetAllAnswers() {
        // Given
        Mockito.when(answerDAO.findAll()).thenReturn(null);

        // When
        List<AnswerDTO> answers = answerService.getAllAnswers();

        //Then
        assertEquals(null, answers);
    }

    @Test
    public void shouldGetAnswerById() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.of(answerToReturn));

        // When
        AnswerDTO answer = answerService.getAnswerById(id);

        //Then
        assertEquals(answerToReturn.getId(), answer.getId());
        assertEquals(answerToReturn.getWording(), answer.getWording());
        assertEquals(answerToReturn.isCorrect(), answer.isCorrect());
        assertEquals(answerToReturn.getQuestion().getId(), answer.getQuestionId());
    }

    @Test
    public void shouldNotGetAnswerById() {
        // Given
        Long id = 1L;
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.empty());

        // When
        AnswerDTO answer = answerService.getAnswerById(id);

        //Then
        assertEquals(null, answer);
    }

}
