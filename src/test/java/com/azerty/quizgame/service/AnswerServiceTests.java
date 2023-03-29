package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.utils.AnswerMapper;
import org.junit.Test;
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
public class AnswerServiceTests {

    @InjectMocks
    private AnswerServiceImplementation answerService;
    @Mock
    private AnswerDAO answerDAO;
    @Mock
    private QuestionDAO questionDAO;

    private final AnswerMapper answerMapper = new AnswerMapper();


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

        // Then
        assertEquals(answersToReturn.size(), answers.size());
    }

    @Test
    public void shouldNotGetAllAnswers() {
        // Given
        Mockito.when(answerDAO.findAll()).thenReturn(null);

        // When
        List<AnswerDTO> answers = answerService.getAllAnswers();

        // Then
        assertNull(answers);
    }

    @Test
    public void shouldGetAnswerById() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.of(answerToReturn));

        // When
        AnswerDTO answer = answerService.getAnswerById(id);

        // Then
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

        // Then
        assertNull(answer);
    }

    @Test
    public void shouldSaveAnswer() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(answerDAO.save(any())).thenReturn(answerToReturn);

        // When
        AnswerDTO answer = answerService.saveAnswer(answerMapper.toAnswerDTO(answerToReturn));

        // Then
        assertEquals(answerToReturn.getId(), answer.getId());
        assertEquals(answerToReturn.getWording(), answer.getWording());
        assertEquals(answerToReturn.isCorrect(), answer.isCorrect());
        assertEquals(answerToReturn.getQuestion().getId(), answer.getQuestionId());
    }

    @Test
    public void shouldNotSaveAnswer() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.empty());

        // When
        AnswerDTO answer = answerService.saveAnswer(answerMapper.toAnswerDTO(answerToReturn));

        // Then
        assertNull(answer);
    }

    @Test
    public void shouldDeleteAnswerById() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        question.setId(2L);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.of(answerToReturn));

        // When
        boolean isDeleted = answerService.deleteAnswerById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeleteAnswerById() {
        // Given
        Long id = 1L;
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = answerService.deleteAnswerById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdateAnswerById() {
        // Given
        Long id = 1L;
        String wording = "The number 42.";
        boolean isCorrect = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Answer answerToReturn = new Answer(id, wording, isCorrect, question);
        String wording2 = "The number 42.0.";
        Answer updatedAnswer = new Answer(id, wording2, isCorrect, question);
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.of(answerToReturn));
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));
        Mockito.when(answerDAO.save(any())).thenReturn(updatedAnswer);

        // When
        AnswerDTO answer = answerService.updateAnswerById(answerMapper.toAnswerDTO(updatedAnswer), id);

        // Then
        assertEquals(updatedAnswer.getId(), answer.getId());
        assertEquals(updatedAnswer.getWording(), answer.getWording());
        assertEquals(updatedAnswer.isCorrect(), answer.isCorrect());
        assertEquals(updatedAnswer.getQuestion().getId(), answer.getQuestionId());
    }

    @Test
    public void shouldNotUpdateAnswerById() {
        // Given
        Long id = 1L;
        String wording = "The number 42.0.";
        boolean isCorrect = true;
        Question question = new Question();
        Long questionId = 2L;
        question.setId(questionId);
        Answer updatedAnswer = new Answer(id, wording, isCorrect, question);
        Mockito.when(answerDAO.findById(id)).thenReturn(Optional.empty());

        // When
        AnswerDTO answer = answerService.updateAnswerById(answerMapper.toAnswerDTO(updatedAnswer), id);

        // Then
        assertNull(answer);
    }

    @Test
    public void shouldGetAllAnswersByQuestionId() {
        // Given
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);

        Long answerId1 = 2L;
        String wording1 = "The number 42.";
        boolean isCorrect1 = true;
        Answer answer1 = new Answer(answerId1, wording1, isCorrect1, question);
        Long answerId2 = 3L;
        String wording2 = "Any other number.";
        boolean isCorrect2 = false;
        Answer answer2 = new Answer(answerId2, wording2, isCorrect2, question);
        List<Answer> answersToReturn = new ArrayList<>();
        answersToReturn.add(answer1);
        answersToReturn.add(answer2);
        Mockito.when(answerDAO.findAllAnswersByQuestionId(questionId)).thenReturn(answersToReturn);
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.of(question));

        // When
        List<AnswerDTO> answers = answerService.getAllAnswersByQuestionId(questionId);

        // Then
        assertEquals(answersToReturn.size(), answers.size());
    }

    @Test
    public void shouldNotGetAllAnswersByQuestionId() {
        // Given
        Long questionId = 1L;
        Mockito.when(questionDAO.findById(questionId)).thenReturn(Optional.empty());

        // When
        List<AnswerDTO> answers = answerService.getAllAnswersByQuestionId(questionId);

        // Then
        assertNull(answers);
    }

}
