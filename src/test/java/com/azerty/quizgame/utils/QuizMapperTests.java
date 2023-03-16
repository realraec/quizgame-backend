package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.QuizState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class QuizMapperTests {

    private final QuizMapper quizMapper = new QuizMapper();


    @Test
    public void shouldGoFromQuizToQuizForInternDTO() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        persons.add(new Person());
        QuizState state = QuizState.STARTED;

        Quiz quiz = new Quiz(id, title, summary, questions, persons);
        QuizForInternDTO quizForInternDTO = quizMapper.toQuizForInternDTO(quiz, state);

        Assertions.assertEquals(null, quizForInternDTO.getId());
        Assertions.assertEquals(title, quizForInternDTO.getTitle());
        Assertions.assertEquals(summary, quizForInternDTO.getSummary());
        Assertions.assertEquals(state, quizForInternDTO.getState());
    }

    @Test
    public void shouldGoFromQuizToQuizDTO() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        //Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        persons.add(new Person());
        //Long[] personsIds = persons.stream().map(Intern::getId).toArray(Long[]::new);

        Quiz quiz = new Quiz(id, title, summary, null, null);
        quiz.setQuestions(questions);
        quiz.setPersons(persons);
        QuizDTO quizDTO = quizMapper.toQuizDTO(quiz);

        Assertions.assertEquals(null, quizDTO.getId());
        Assertions.assertEquals(title, quizDTO.getTitle());
        Assertions.assertEquals(summary, quizDTO.getSummary());
        Assertions.assertEquals(questions.size(), quizDTO.getQuestionsIds().length);
        Assertions.assertEquals(persons.size(), quizDTO.getPersonsIds().length);
    }

    @Test
    public void shouldGoFromQuizDTOToQuiz() {
        Long id = 1L;
        String title = "Life";
        String summary = "Pretty deep stuff.";
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        Long[] questionsIds = questions.stream().map(Question::getId).toArray(Long[]::new);
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        persons.add(new Person());
        Long[] personsIds = persons.stream().map(Person::getId).toArray(Long[]::new);

        QuizDTO quizDTO = new QuizDTO(id, title, summary, questionsIds, personsIds);
        Quiz quiz = quizMapper.toQuiz(quizDTO);

        Assertions.assertEquals(null, quiz.getId());
        Assertions.assertEquals(title, quiz.getTitle());
        Assertions.assertEquals(summary, quiz.getSummary());
        Assertions.assertEquals(0, quiz.getQuestions().size());
        Assertions.assertEquals(0, quiz.getPersons().size());
    }

}
