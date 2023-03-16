package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
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
        assertEquals(null, quizzes);
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
        assertEquals(null, quiz);
    }

}
