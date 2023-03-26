package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.AnswerInQuizDTO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.model.entity.Answer;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.utils.QuestionMapper;
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

    private final QuestionMapper questionMapper = new QuestionMapper();


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

        // Then
        assertEquals(questionsToReturn.size(), questions.size());
    }

    @Test
    public void shouldNotGetAllQuestions() {
        // Given
        Mockito.when(questionDAO.findAll()).thenReturn(null);

        // When
        List<QuestionDTO> questions = questionService.getAllQuestions();

        // Then
        assertNull(questions);
    }

    @Test
    public void shouldGetQuestionById() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        Answer answer1 = new Answer();
        answer1.setId(3L);
        Answer answer2 = new Answer();
        answer2.setId(4L);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.of(questionToReturn));

        // When
        QuestionDTO question = questionService.getQuestionById(id);

        // Then
        assertEquals(questionToReturn.getId(), question.getId());
        assertEquals(questionToReturn.getWording(), question.getWording());
        assertEquals(questionToReturn.getMaxDurationInSeconds(), question.getMaxDurationInSeconds());
        assertEquals(questionToReturn.getQuiz().getId(), question.getQuizId());
        assertEquals(answersIds.length, question.getAnswersIds().length);
    }

    @Test
    public void shouldNotGetQuestionById() {
        // Given
        Long id = 1L;
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.empty());

        // When
        QuestionDTO question = questionService.getQuestionById(id);

        // Then
        assertNull(question);
    }

    @Test
    public void shouldSaveQuestion() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        Answer answer1 = new Answer();
        Long answerId1 = 3L;
        answer1.setId(answerId1);
        Answer answer2 = new Answer();
        Long answerId2 = 4L;
        answer2.setId(answerId2);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(answerDAO.findById(answerId1)).thenReturn(Optional.of(answer1));
        Mockito.when(answerDAO.findById(answerId2)).thenReturn(Optional.of(answer2));
        Mockito.when(questionDAO.save(any())).thenReturn(questionToReturn);

        // When
        QuestionDTO question = questionService.saveQuestion(questionMapper.toQuestionDTO(questionToReturn));

        // Then
        assertEquals(questionToReturn.getId(), question.getId());
        assertEquals(wording, question.getWording());
        assertEquals(maxDurationInSeconds, question.getMaxDurationInSeconds());
        assertEquals(quiz.getId(), question.getQuizId());
        assertEquals(answersIds.length, question.getAnswersIds().length);
    }

    @Test
    public void shouldSaveQuestionNoAnswers() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        List<Answer> answers = new ArrayList<>();
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(questionDAO.save(any())).thenReturn(questionToReturn);

        // When
        QuestionDTO question = questionService.saveQuestion(questionMapper.toQuestionDTO(questionToReturn));

        // Then
        assertEquals(questionToReturn.getId(), question.getId());
        assertEquals(wording, question.getWording());
        assertEquals(maxDurationInSeconds, question.getMaxDurationInSeconds());
        assertEquals(quiz.getId(), question.getQuizId());
        assertEquals(answersIds.length, question.getAnswersIds().length);
    }


    @Test
    public void shouldNotSaveQuestionQuiz() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        List<Answer> answers = new ArrayList<>();
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        QuestionDTO question = questionService.saveQuestion(questionMapper.toQuestionDTO(questionToReturn));

        // Then
        assertNull(question);
    }

    @Test
    public void shouldNotSaveQuestionAnswer() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        Answer answer1 = new Answer();
        Long answerId1 = 3L;
        answer1.setId(answerId1);
        Answer answer2 = new Answer();
        Long answerId2 = 4L;
        answer2.setId(answerId2);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(answerDAO.findById(answerId1)).thenReturn(Optional.empty());

        // When
        QuestionDTO question = questionService.saveQuestion(questionMapper.toQuestionDTO(questionToReturn));

        // Then
        assertNull(question);
    }

    @Test
    public void shouldDeleteQuestionById() {
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
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.of(questionToReturn));

        // When
        boolean isDeleted = questionService.deleteQuestionById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeleteQuestionById() {
        // Given
        Long id = 1L;
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = questionService.deleteQuestionById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdateQuestionById() {
        // Given
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        List<Answer> answers = new ArrayList<>();
        Long[] answersIds = answers.stream().map(Answer::getId).toArray(Long[]::new);
        Question questionToReturn = new Question(id, wording, maxDurationInSeconds, quiz, answers);
        String wording2 = "What is the REAL meaning of life?";
        Question updatedQuestion = new Question(id, wording2, maxDurationInSeconds, quiz, answers);
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.of(questionToReturn));
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(questionDAO.save(any())).thenReturn(updatedQuestion);

        // When
        QuestionDTO question = questionService.updateQuestionById(questionMapper.toQuestionDTO(updatedQuestion), id);

        // Then
        assertEquals(updatedQuestion.getId(), question.getId());
        assertEquals(updatedQuestion.getWording(), question.getWording());
        assertEquals(updatedQuestion.getMaxDurationInSeconds(), question.getMaxDurationInSeconds());
        assertEquals(updatedQuestion.getQuiz().getId(), question.getQuizId());
        assertEquals(answersIds.length, question.getAnswersIds().length);
    }

    @Test
    public void shouldNotUpdateQuestionById() {
        // Given
        Long id = 1L;
        int maxDurationInSeconds = 30;
        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        List<Answer> answers = new ArrayList<>();
        String wording2 = "What is the REAL meaning of life?";
        Question updatedQuestion = new Question(id, wording2, maxDurationInSeconds, quiz, answers);
        Mockito.when(questionDAO.findById(id)).thenReturn(Optional.empty());

        // When
        QuestionDTO question = questionService.updateQuestionById(questionMapper.toQuestionDTO(updatedQuestion), id);

        // Then
        assertNull(question);
    }

    @Test
    public void shouldGetAllQuestionsByQuizId() {
        // Given
        Quiz quiz = new Quiz();
        Long quizId = 5L;
        quiz.setId(quizId);

        Long questionId1 = 1L;
        String wording1 = "What is the meaning of life?";
        int maxDurationInSeconds1 = 30;
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer());
        answers1.add(new Answer());
        Question question1 = new Question(questionId1, wording1, maxDurationInSeconds1, quiz, answers1);

        Long questionId2 = 2L;
        String wording2 = "What is the meaning of life?";
        int maxDurationInSeconds2 = 30;
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer());
        answers2.add(new Answer());
        Question question2 = new Question(questionId2, wording2, maxDurationInSeconds2, quiz, answers2);

        ArrayList<Question> questionsToReturn = new ArrayList<>();
        questionsToReturn.add(question1);
        questionsToReturn.add(question2);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(questionDAO.findAllByQuizId(quizId)).thenReturn(questionsToReturn);

        // When
        List<QuestionDTO> questions = questionService.getAllQuestionsByQuizId(quizId);

        // Then
        assertEquals(questionsToReturn.size(), questions.size());
    }

    @Test
    public void shouldNotGetAllQuestionsByQuizId() {
        // Given
        Long quizId = 5L;
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        List<QuestionDTO> questions = questionService.getAllQuestionsByQuizId(quizId);

        // Then
        assertNull(questions);
    }

    @Test
    public void shouldGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId() {
        // Given
        Long questionId = 1L;
        String questionWording = "What is the meaning of life?";
        int questionMaxDurationInSeconds = 30;
        String answerWording1 = "Everything.";
        String answerWording2 = "Nothing.";
        Long answerId1 = 3L;
        Long answerId2 = 4L;
        boolean answerIsCorrect1 = true;
        boolean answerIsCorrect2 = false;
        Long numberOfQuestionsLeft = 1L;

        List<Object[]> objectArraysListToReturn = new ArrayList<>();
        Object[] object2 = {questionId, questionWording, questionMaxDurationInSeconds, answerId1, answerIsCorrect1, answerWording1, numberOfQuestionsLeft};
        Object[] object3 = {questionId, questionWording, questionMaxDurationInSeconds, answerId2, answerIsCorrect2, answerWording2, numberOfQuestionsLeft};
        objectArraysListToReturn.add(object2);
        objectArraysListToReturn.add(object3);

        Progress progressToReturn = new Progress();
        Long progressId = 5L;
        progressToReturn.setId(progressId);

        Quiz quiz = new Quiz();
        Long quizId = 2L;
        quiz.setId(quizId);
        AnswerInQuizDTO answer1 = new AnswerInQuizDTO();
        answer1.setId(answerId1);
        answer1.setWording(answerWording1);
        answer1.setCorrect(answerIsCorrect1);
        AnswerInQuizDTO answer2 = new AnswerInQuizDTO();
        answer2.setId(answerId2);
        answer2.setWording(answerWording2);
        answer2.setCorrect(answerIsCorrect2);
        List<AnswerInQuizDTO> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        AnswerInQuizDTO[] answersAsArray = answers.toArray(AnswerInQuizDTO[]::new);
        QuestionInQuizDTO questionToReturn = new QuestionInQuizDTO(questionId, questionWording, questionMaxDurationInSeconds, answersAsArray, numberOfQuestionsLeft);
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.of(progressToReturn));
        Mockito.when(questionDAO.findOneQuestionInQuizTogetherWithAllItsAnswersWithIdNotInProgressRecordsByProgressId(progressId))
                .thenReturn(objectArraysListToReturn);

        // When
        QuestionInQuizDTO questionWithExtra = questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(progressId);

        // Then
        assertEquals(questionToReturn.getId(), questionWithExtra.getId());
        assertEquals(questionToReturn.getWording(), questionWithExtra.getWording());
        assertEquals(questionToReturn.getMaxDurationInSeconds(), questionWithExtra.getMaxDurationInSeconds());
        assertEquals(questionToReturn.getAnswers().length, questionWithExtra.getAnswers().length);
        assertEquals(questionToReturn.getNumberOfQuestionsLeft(), questionWithExtra.getNumberOfQuestionsLeft());
    }

    @Test
    public void shouldNotGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId() {
        // Given
        Long progressId = 5L;
        Mockito.when(progressDAO.findById(progressId)).thenReturn(Optional.empty());

        // When
        QuestionInQuizDTO questionWithExtra = questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(progressId);

        // Then
        assertNull(questionWithExtra);
    }

}
