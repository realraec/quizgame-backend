package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
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
public class QuestionServiceTests {

    @InjectMocks
    private QuestionServiceImplementation questionService;
    @Mock
    private QuestionDAO questionDAO;
    @Mock
    private ProgressDAO progressDAO;
    @Mock
    private QuizDAO quizDAO;
    @Mock
    private AnswerDAO answerDAO;

    @Test
    public void shouldGetAllQuestions() {
        // Given
        Long id1 = 1L;
        String wording1 = "What is the meaning of life?";
        int maxDurationInSeconds1 = 30;
        Quiz quiz1 = new Quiz();
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer());
        answers1.add(new Answer());
        Question question1 = new Question(id1, wording1, maxDurationInSeconds1, quiz1, answers1);
        Long id2 = 2L;
        String wording2 = "What is the meaning of life?";
        int maxDurationInSeconds2 = 30;
        Quiz quiz2 = new Quiz();
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer());
        answers2.add(new Answer());
        Question question2 = new Question(id2, wording2, maxDurationInSeconds2, quiz2, answers2);
        ArrayList<Question> questionsToReturn = new ArrayList<>();
        questionsToReturn.add(question1);
        questionsToReturn.add(question2);
        Mockito.when(questionDAO.findAll()).thenReturn(questionsToReturn);

        // When
        List<QuestionDTO> questions = questionService.getAllQuestions();

        //Then
        assertEquals(questionsToReturn.size(), questions.size());
    }

    @Test
    public void shouldNotGetAllQuestions() {
        // Given
        Mockito.when(questionDAO.findAll()).thenReturn(null);

        // When
        List<QuestionDTO> questions = questionService.getAllQuestions();

        //Then
        assertEquals(null, questions);
    }

    @Test
    public void shouldGetQuestionById() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Answer answer1 = new Answer();
        answer1.setId(2L);
        Answer answer2 = new Answer();
        answer2.setId(3L);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.of(questionToReturn));

        // When
        QuestionDTO question = questionService.getQuestionById(id);

        //Then
        assertEquals(questionToReturn.getId(), question.getId());
        assertEquals(wording, question.getWording());
        assertEquals(maxDurationInSeconds, question.getMaxDurationInSeconds());
        assertEquals(quiz.getId(), question.getQuizId());
        assertEquals(answersIds.length, question.getAnswersIds().length);
    }

    @Test
    public void shouldNotGetQuestionById() {
        // Given
        Long id = 1L;
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.empty());

        // When
        QuestionDTO question = questionService.getQuestionById(id);

        //Then
        assertEquals(null, question);
    }

}
