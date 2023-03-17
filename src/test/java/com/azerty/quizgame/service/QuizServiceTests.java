package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.utils.QuizMapper;
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
public class QuizServiceTests {

    @InjectMocks
    private QuizServiceImplementation quizService;
    @Mock
    private QuizDAO quizDAO;
    @Mock
    private ProgressDAO progressDAO;
    @Mock
    private PersonDAO personDAO;
    @Mock
    private QuestionDAO questionDAO;

    private final QuizMapper quizMapper = new QuizMapper();


    @Test
    public void shouldGetAllQuizzes() {
        // Given
        Long id1 = 1L;
        String title1 = "Life";
        String summary1 = "Pretty deep stuff.";
        List<Question> questions1 = new ArrayList<>();
        questions1.add(new Question());
        questions1.add(new Question());
        List<Person> persons1 = new ArrayList<>();
        persons1.add(new Person());
        persons1.add(new Person());
        Quiz quiz1 = new Quiz(id1, title1, summary1, questions1, persons1);
        Long id2 = 2L;
        String title2 = "Life";
        String summary2 = "Pretty deep stuff.";
        List<Question> questions2 = new ArrayList<>();
        questions2.add(new Question());
        questions2.add(new Question());
        List<Person> persons2 = new ArrayList<>();
        persons2.add(new Person());
        persons2.add(new Person());
        Quiz quiz2 = new Quiz(id2, title2, summary2, questions2, persons2);
        List<Quiz> quizzesToReturn = new ArrayList<>();
        quizzesToReturn.add(quiz1);
        quizzesToReturn.add(quiz2);
        Mockito.when(quizDAO.findAll()).thenReturn(quizzesToReturn);

        // When
        List<QuizDTO> quizzes = quizService.getAllQuizzes();

        //Then
        assertEquals(quizzesToReturn.size(), quizzes.size());
    }

    @Test
    public void shouldNotGetAllQuizzes() {
        // Given
        Mockito.when(quizDAO.findAll()).thenReturn(null);

        // When
        List<QuizDTO> quizzes = quizService.getAllQuizzes();

        //Then
        assertNull(quizzes);
    }

    @Test
    public void shouldGetQuizById() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Question question1 = new Question();
        question1.setId(2L);
        Question question2 = new Question();
        question2.setId(3L);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        Person person1 = new Person();
        person1.setId(4L);
        Person person2 = new Person();
        person2.setId(5L);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        Long[] personsIds = persons.stream().map(Person::getId).toArray(Long[]::new);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.of(quizToReturn));

        // When
        QuizDTO quiz = quizService.getQuizById(id);

        //Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(personsIds.length, quiz.getPersonsIds().length);
    }

    @Test
    public void shouldNotGetQuizById() {
        // Given
        Long id = 1L;
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.getQuizById(id);

        //Then
        assertNull(quiz);
    }

    @Test
    public void shouldSaveQuiz() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Question question1 = new Question();
        Long questionId1 = 2L;
        question1.setId(questionId1);
        Question question2 = new Question();
        Long questionId2 = 3L;
        question2.setId(questionId2);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        Person person1 = new Person();
        Long personId1 = 4L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 5L;
        person2.setId(personId2);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        Long[] personsIds = persons.stream().map(Person::getId).toArray(Long[]::new);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.of(person1));
        Mockito.when(personDAO.findById(personId2)).thenReturn(Optional.of(person2));
        Mockito.when(questionDAO.findById(questionId1)).thenReturn(Optional.of(question1));
        Mockito.when(questionDAO.findById(questionId2)).thenReturn(Optional.of(question2));
        Mockito.when(quizDAO.save(any())).thenReturn(quizToReturn);

        // When
        QuizDTO quiz = quizService.saveQuiz(quizMapper.toQuizDTO(quizToReturn));

        // Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(personsIds.length, quiz.getPersonsIds().length);
    }

    @Test
    public void shouldSaveQuizNoQuestionsNoPersons() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        List<Person> persons = new ArrayList<>();
        Long[] personsIds = persons.stream().map(Person::getId).toArray(Long[]::new);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(quizDAO.save(any())).thenReturn(quizToReturn);

        // When
        QuizDTO quiz = quizService.saveQuiz(quizMapper.toQuizDTO(quizToReturn));

        // Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(personsIds.length, quiz.getPersonsIds().length);
    }

    @Test
    public void shouldNotSaveQuizPerson() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        Person person1 = new Person();
        Long personId1 = 4L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 5L;
        person2.setId(personId2);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.saveQuiz(quizMapper.toQuizDTO(quizToReturn));

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldNotSaveQuizQuestion() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Question question1 = new Question();
        Long questionId1 = 2L;
        question1.setId(questionId1);
        Question question2 = new Question();
        Long questionId2 = 3L;
        question2.setId(questionId2);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        List<Person> persons = new ArrayList<>();
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(questionDAO.findById(questionId1)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.saveQuiz(quizMapper.toQuizDTO(quizToReturn));

        // Then
        assertNull(quiz);
    }


    @Test
    public void shouldDeleteQuizById() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Question question1 = new Question();
        question1.setId(2L);
        Question question2 = new Question();
        question2.setId(3L);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        Person person1 = new Person();
        person1.setId(4L);
        Person person2 = new Person();
        person2.setId(5L);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.of(quizToReturn));

        // When
        boolean isDeleted = quizService.deleteQuizById(id);

        // Then
        assertTrue(isDeleted);
    }

    @Test
    public void shouldNotDeleteQuizById() {
        // Given
        Long id = 1L;
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.empty());

        // When
        boolean isDeleted = quizService.deleteQuizById(id);

        // Then
        assertFalse(isDeleted);
    }

    @Test
    public void shouldUpdateQuizById() {
        // Given
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        List<Person> persons = new ArrayList<>();
        Long[] personsIds = persons.stream().map(Person::getId).toArray(Long[]::new);
        Quiz quizToReturn = new Quiz(id, title, summary, questions, persons);
        String title2 = "Real Life";
        Quiz updatedQuiz = new Quiz(id, title2, summary, questions, persons);
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(quizDAO.save(any())).thenReturn(updatedQuiz);

        // When
        QuizDTO quiz = quizService.updateQuizById(quizMapper.toQuizDTO(updatedQuiz), id);

        //Then
        assertEquals(updatedQuiz.getId(), quiz.getId());
        assertEquals(updatedQuiz.getTitle(), quiz.getTitle());
        Assertions.assertEquals(updatedQuiz.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(personsIds.length, quiz.getPersonsIds().length);
    }

    @Test
    public void shouldNotUpdateQuizById() {
        // Given
        Long id = 1L;
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        String title2 = "Real Life";
        Quiz updatedQuiz = new Quiz(id, title2, summary, questions, persons);
        Mockito.when(quizDAO.findById(id)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.updateQuizById(quizMapper.toQuizDTO(updatedQuiz), id);

        //Then
        assertNull(quiz);
    }

}
