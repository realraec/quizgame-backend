package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.model.entity.*;
import com.azerty.quizgame.model.enums.QuizState;
import com.azerty.quizgame.utils.QuizMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

        // Then
        assertEquals(quizzesToReturn.size(), quizzes.size());
    }

    @Test
    public void shouldNotGetAllQuizzes() {
        // Given
        Mockito.when(quizDAO.findAll()).thenReturn(null);

        // When
        List<QuizDTO> quizzes = quizService.getAllQuizzes();

        // Then
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

        // Then
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

        // Then
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

        // Then
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

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldGetAllQuizzesAttributedToPersonWithStateByPersonId() {
        // Given
        Person person = new Person();
        Long personId = 5L;
        person.setId(personId);
        QuizState quizState1 = QuizState.COMPLETED;
        QuizState quizState2 = QuizState.NOT_STARTED;

        Long quizId1 = 1L;
        String title1 = "Life";
        String summary1 = "Pretty deep stuff.";
        List<Question> questions1 = new ArrayList<>();
        questions1.add(new Question());
        questions1.add(new Question());
        List<Person> persons1 = new ArrayList<>();
        persons1.add(person);
        Quiz quiz1 = new Quiz(quizId1, title1, summary1, questions1, persons1);
        quiz1.setId(quizId1);

        Long quizId2 = 2L;
        String title2 = "Life";
        String summary2 = "Pretty deep stuff.";
        List<Question> questions2 = new ArrayList<>();
        questions2.add(new Question());
        questions2.add(new Question());
        List<Person> persons2 = new ArrayList<>();
        persons2.add(person);
        Quiz quiz2 = new Quiz(quizId2, title2, summary2, questions2, persons2);
        quiz2.setId(quizId2);

        List<Quiz> quizzesToReturn = new ArrayList<>();
        quizzesToReturn.add(quiz1);
        quizzesToReturn.add(quiz2);

        Long progressId = 1L;
        LocalDateTime dateAndTimeOfCompletion = LocalDateTime.now();
        int score = 3;
        List<Record> records = new ArrayList<>();
        Progress progressToReturn = new Progress(progressId, dateAndTimeOfCompletion, score, person, quiz1, records);

        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(quizDAO.findAllQuizzesByPersonId(personId)).thenReturn(quizzesToReturn);
        Mockito.when(progressDAO.findProgressByPersonIdAndQuizId(personId, quizId1)).thenReturn(Optional.of(progressToReturn));
        Mockito.when(progressDAO.findProgressByPersonIdAndQuizId(personId, quizId2)).thenReturn(Optional.empty());

        // When
        List<QuizForInternDTO> quizzes = quizService.getAllQuizzesAttributedToPersonWithStateByPersonId(personId);

        // Then
        assertEquals(quizzesToReturn.size(), quizzes.size());
        assertEquals(quizzesToReturn.get(0).getId(), quizzes.get(0).getId());
        assertEquals(quizzesToReturn.get(0).getTitle(), quizzes.get(0).getTitle());
        assertEquals(quizzesToReturn.get(0).getSummary(), quizzes.get(0).getSummary());
        assertEquals(quizState1, quizzes.get(0).getState());
        assertEquals(quizzesToReturn.get(1).getId(), quizzes.get(1).getId());
        assertEquals(quizzesToReturn.get(1).getTitle(), quizzes.get(1).getTitle());
        assertEquals(quizzesToReturn.get(1).getSummary(), quizzes.get(1).getSummary());
        assertEquals(quizState2, quizzes.get(1).getState());
    }

    @Test
    public void shouldNotGetAllQuizzesAttributedToPersonWithStateByPersonId() {
        // Given
        Long personId = 1L;
        Mockito.when(personDAO.findById(personId)).thenReturn(Optional.empty());

        // When
        List<QuizForInternDTO> quizzes = quizService.getAllQuizzesAttributedToPersonWithStateByPersonId(personId);

        // Then
        assertNull(quizzes);
    }

    @Test
    public void shouldAttributePersonsToQuizByIds() {
        // Given
        Person person1 = new Person();
        Long personId1 = 2L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 3L;
        person2.setId(personId2);
        Person person3 = new Person();
        Long personId3 = 4L;
        person3.setId(personId3);
        Long[] personsIds = {personId1, personId2};
        List<Person> persons = new ArrayList<>();
        persons.add(person3);
        List<Person> updatedPersons = new ArrayList<>();
        updatedPersons.add(person3);
        updatedPersons.add(person1);
        updatedPersons.add(person2);
        Long[] updatedPersonsIds = updatedPersons.stream().map(Person::getId).toArray(Long[]::new);

        Question question1 = new Question();
        Long questionId1 = 5L;
        question1.setId(questionId1);
        Question question2 = new Question();
        Long questionId2 = 6L;
        question2.setId(questionId2);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);

        Long quizId = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Quiz quizToReturn = new Quiz(quizId, title, summary, questions, persons);
        quizToReturn.setId(quizId);
        Quiz updatedQuiz = new Quiz(quizId, title, summary, questions, updatedPersons);
        updatedQuiz.setId(quizId);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.of(person1));
        Mockito.when(personDAO.findById(personId2)).thenReturn(Optional.of(person2));
        Mockito.when(questionDAO.findById(questionId1)).thenReturn(Optional.of(question1));
        Mockito.when(questionDAO.findById(questionId2)).thenReturn(Optional.of(question2));
        Mockito.when(personDAO.findById(personId3)).thenReturn(Optional.of(person3));
        Mockito.when(quizDAO.save(any())).thenReturn(updatedQuiz);

        // When
        QuizDTO quiz = quizService.attributePersonsToQuizByIds(quizId, personsIds);

        // Then
        //assertEquals(updatedQuiz.getId(), quiz.getId());
        assertEquals(updatedQuiz.getTitle(), quiz.getTitle());
        Assertions.assertEquals(updatedQuiz.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(questionsIds[0], quiz.getQuestionsIds()[0]);
        Assertions.assertEquals(questionsIds[1], quiz.getQuestionsIds()[1]);
        Assertions.assertEquals(updatedPersonsIds.length, quiz.getPersonsIds().length);
        Assertions.assertEquals(updatedPersonsIds[0], quiz.getPersonsIds()[0]);
        Assertions.assertEquals(updatedPersonsIds[1], quiz.getPersonsIds()[1]);
        Assertions.assertEquals(updatedPersonsIds[2], quiz.getPersonsIds()[2]);
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIdsQuiz() {
        // Given
        Long quizId = 1L;
        Long personId1 = 2L;
        Long personId2 = 3L;
        Long[] personsIds = {personId1, personId2};
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.attributePersonsToQuizByIds(quizId, personsIds);

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIdsPersonsIdsParameter() {
        // Given
        Long quizId = 1L;
        Long personId = 2L;
        Long[] personsIds = {personId, personId};
        QuizDTO quizToReturn = new QuizDTO();

        // When
        QuizDTO quiz = quizService.attributePersonsToQuizByIds(quizId, personsIds);

        // Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertNull(quiz.getQuestionsIds());
        Assertions.assertNull(quiz.getPersonsIds());
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIdsPerson() {
        // Given
        Long quizId = 1L;
        Long personId1 = 2L;
        Long personId2 = 3L;
        Long[] personsIds = {personId1, personId2};
        Quiz quizToReturn = new Quiz();
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.attributePersonsToQuizByIds(quizId, personsIds);

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldNotAttributePersonsToQuizByIdsPersonsIds() {
        // Given
        Person person1 = new Person();
        Long personId1 = 2L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 3L;
        person2.setId(personId2);
        Long[] personsIds = {personId1, personId2};
        List<Person> persons = new ArrayList<>();
        persons.add(person1);

        Long quizId = 1L;
        Quiz quizToReturn = new Quiz();
        quizToReturn.setId(quizId);
        quizToReturn.setPersons(persons);
        Quiz quizEmpty = new Quiz();
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.of(person1));
        Mockito.when(personDAO.findById(personId2)).thenReturn(Optional.of(person2));

        // When
        QuizDTO quiz = quizService.attributePersonsToQuizByIds(quizId, personsIds);

        // Then
        assertEquals(quizEmpty.getId(), quiz.getId());
        assertEquals(quizEmpty.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizEmpty.getSummary(), quiz.getSummary());
        Assertions.assertNull(quiz.getQuestionsIds());
        Assertions.assertNull(quiz.getPersonsIds());
    }

    @Test
    public void shouldRemovePersonsFromQuizByIds() {
        // Given
        Person person1 = new Person();
        Long personId1 = 2L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 3L;
        person2.setId(personId2);
        Person person3 = new Person();
        Long personId3 = 4L;
        person3.setId(personId3);
        Long[] personsIds = {personId1, personId2};
        List<Person> persons = new ArrayList<>(List.of(person1, person2, person3));
        List<Person> updatedPersons = new ArrayList<>(List.of(person3));
        Long[] updatedPersonsIds = updatedPersons.stream().map(Person::getId).toArray(Long[]::new);

        Question question1 = new Question();
        Long questionId1 = 5L;
        question1.setId(questionId1);
        Question question2 = new Question();
        Long questionId2 = 6L;
        question2.setId(questionId2);
        List<Question> questions = new ArrayList<>(List.of(question1, question2));
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);

        Long quizId = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        Quiz quizToReturn = new Quiz(quizId, title, summary, questions, persons);
        quizToReturn.setId(quizId);
        Quiz updatedQuiz = new Quiz(quizId, title, summary, questions, updatedPersons);
        updatedQuiz.setId(quizId);
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.of(person1));
        Mockito.when(personDAO.findById(personId2)).thenReturn(Optional.of(person2));
        Mockito.when(questionDAO.findById(questionId1)).thenReturn(Optional.of(question1));
        Mockito.when(questionDAO.findById(questionId2)).thenReturn(Optional.of(question2));
        Mockito.when(personDAO.findById(personId3)).thenReturn(Optional.of(person3));
        Mockito.when(quizDAO.save(any())).thenReturn(updatedQuiz);

        // When
        QuizDTO quiz = quizService.removePersonsFromQuizByIds(quizId, personsIds);

        // Then
        //assertEquals(updatedQuiz.getId(), quiz.getId());
        assertEquals(updatedQuiz.getTitle(), quiz.getTitle());
        Assertions.assertEquals(updatedQuiz.getSummary(), quiz.getSummary());
        Assertions.assertEquals(questionsIds.length, quiz.getQuestionsIds().length);
        Assertions.assertEquals(questionsIds[0], quiz.getQuestionsIds()[0]);
        Assertions.assertEquals(questionsIds[1], quiz.getQuestionsIds()[1]);
        Assertions.assertEquals(updatedPersonsIds.length, quiz.getPersonsIds().length);
        Assertions.assertEquals(updatedPersonsIds[0], quiz.getPersonsIds()[0]);
    }

    @Test
    public void shouldNotRemovePersonsFromQuizByIdsQuiz() {
        // Given
        Long quizId = 1L;
        Long personId1 = 2L;
        Long personId2 = 3L;
        Long[] personsIds = {personId1, personId2};
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.removePersonsFromQuizByIds(quizId, personsIds);

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldNotRemovePersonsFromQuizByIdsPersonsIdsParameter() {
        // Given
        Long quizId = 1L;
        Long personId = 2L;
        Long[] personsIds = {personId, personId};
        QuizDTO quizToReturn = new QuizDTO();

        // When
        QuizDTO quiz = quizService.removePersonsFromQuizByIds(quizId, personsIds);

        // Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertNull(quiz.getQuestionsIds());
        Assertions.assertNull(quiz.getPersonsIds());
    }

    @Test
    public void shouldNotRemovePersonsFromQuizByIdsPerson() {
        // Given
        Long quizId = 1L;
        Long personId1 = 2L;
        Long personId2 = 3L;
        Long[] personsIds = {personId1, personId2};
        Quiz quizToReturn = new Quiz();
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.empty());

        // When
        QuizDTO quiz = quizService.removePersonsFromQuizByIds(quizId, personsIds);

        // Then
        assertNull(quiz);
    }

    @Test
    public void shouldNotRemovePersonsFromQuizByIdsPersonsIds() {
        // Given
        Person person1 = new Person();
        Long personId1 = 2L;
        person1.setId(personId1);
        Person person2 = new Person();
        Long personId2 = 3L;
        person2.setId(personId2);
        Long[] personsIds = {personId1, personId2};

        Long quizId = 1L;
        Quiz quizToReturn = new Quiz();
        Mockito.when(quizDAO.findById(quizId)).thenReturn(Optional.of(quizToReturn));
        Mockito.when(personDAO.findById(personId1)).thenReturn(Optional.of(person1));
        Mockito.when(personDAO.findById(personId2)).thenReturn(Optional.of(person2));

        // When
        QuizDTO quiz = quizService.removePersonsFromQuizByIds(quizId, personsIds);

        // Then
        assertEquals(quizToReturn.getId(), quiz.getId());
        assertEquals(quizToReturn.getTitle(), quiz.getTitle());
        Assertions.assertEquals(quizToReturn.getSummary(), quiz.getSummary());
        Assertions.assertNull(quiz.getQuestionsIds());
        Assertions.assertNull(quiz.getPersonsIds());
    }

}
