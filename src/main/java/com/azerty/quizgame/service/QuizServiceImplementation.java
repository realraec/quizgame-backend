package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.QuizState;
import com.azerty.quizgame.utils.QuizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizServiceImplementation implements QuizService {

    private final QuizDAO quizDAO;
    private final ProgressDAO progressDAO;
    private final PersonDAO personDAO;
    private final QuestionDAO questionDAO;
    private final QuizMapper quizMapper = new QuizMapper();


    @Autowired
    public QuizServiceImplementation(QuizDAO quizDAO, ProgressDAO progressDAO, PersonDAO personDAO,
                                     QuestionDAO questionDAO) {
        this.quizDAO = quizDAO;
        this.progressDAO = progressDAO;
        this.personDAO = personDAO;
        this.questionDAO = questionDAO;
    }


    @Override
    public List<QuizDTO> getAllQuizzes() {
        try {
            Iterator<Quiz> quizIterator = quizDAO.findAll().iterator();
            List<QuizDTO> quizzes = new ArrayList<>();
            while (quizIterator.hasNext()) {
                quizzes.add(quizMapper.toQuizDTO(quizIterator.next()));
            }
            return quizzes;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public QuizDTO getQuizById(Long id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        return quiz.map(quizMapper::toQuizDTO).orElse(null);
    }

    @Override
    public QuizDTO saveQuiz(QuizDTO quiz) {
        Long[] questionsIds = quiz.getQuestionsIds();
        if (questionsIds != null && questionsIds.length > 0) {
            for (int i = 0; i < questionsIds.length; i++) {
                Optional<Question> checkQuestion = questionDAO.findById(questionsIds[i]);
                if (checkQuestion.isEmpty()) {
                    return null;
                }
            }
        } else {
            quiz.setQuestionsIds(new Long[]{});
        }

        Long[] personsIds = quiz.getPersonsIds();
        if (personsIds != null && personsIds.length > 0) {
            for (int i = 0; i < personsIds.length; i++) {
                Optional<Person> checkPerson = personDAO.findById(personsIds[i]);
                if (checkPerson.isEmpty()) {
                    return null;
                }
            }
        } else {
            quiz.setPersonsIds(new Long[]{});
        }

        Quiz quizAsEntity = quizMapper.toQuiz(quiz);
        quizAsEntity.setId(quiz.getId());
        return quizMapper.toQuizDTO(quizDAO.save(quizAsEntity));
    }

    @Override
    public boolean deleteQuizById(Long id) {
        Optional<Quiz> checkQuiz = quizDAO.findById(id);
        if (checkQuiz.isPresent()) {
            quizDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public QuizDTO updateQuizById(QuizDTO quiz, Long id) {
        Optional<Quiz> checkQuiz = quizDAO.findById(id);
        if (checkQuiz.isPresent()) {
            Quiz quizAsEntity = quizMapper.toQuiz(quiz);
            quizAsEntity.setId(id);
            return saveQuiz(quizMapper.toQuizDTO(quizAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public List<QuizForInternDTO> getAllQuizzesAttributedToPersonWithStateByPersonId(Long personId) {
        Optional<Person> checkPerson = personDAO.findById(personId);
        if (checkPerson.isPresent()) {

            Iterator<Quiz> quizIterator = quizDAO.findAllQuizzesByPersonId(personId).iterator();
            List<QuizForInternDTO> quizzesForIntern = new ArrayList<>();
            while (quizIterator.hasNext()) {
                Quiz quiz = quizIterator.next();
                QuizState quizState = QuizState.NOT_STARTED;
                Optional<Progress> progress = progressDAO.findProgressByPersonIdAndQuizId(personId, quiz.getId());
                Long progressId = null;
                if (progress.isPresent()) {
                    quizState = QuizState.STARTED;
                    progressId = progress.get().getId();
                    if (progress.get().getDateAndTimeOfCompletion() != null) {
                        quizState = QuizState.COMPLETED;
                    }
                }
                quizzesForIntern.add(quizMapper.toQuizForInternDTO(quiz, quizState, progressId));
            }
            return quizzesForIntern;
        } else {
            return null;
        }
    }

    @Override
    public QuizDTO attributePersonsToQuizByIds(Long quizId, Long[] personsIds) throws Exception {
        Set<Long> personsIdsAsSet = new HashSet<>(Arrays.asList(personsIds));
        if (personsIdsAsSet.size() != personsIds.length) {
            return new QuizDTO();
        }

        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        if (checkQuiz.isPresent()) {
            List<Person> personsToAttribute = new ArrayList<>();
            for (int i = 0; i < personsIds.length; i++) {
                Optional<Person> checkPerson = personDAO.findById(personsIds[i]);
                if (checkPerson.isEmpty()) {
                    return null;
                }
                personsToAttribute.add(checkPerson.get());
            }
            Quiz quizAsEntity = checkQuiz.get();
            List<Person> persons = quizAsEntity.getPersons();
            for (int i = 0; i < persons.size(); i++) {
                for (int j = 0; j < personsIds.length; j++) {
                    if (Objects.equals(persons.get(i).getId(), personsIds[j])) {
                        return new QuizDTO();
                    }
                }
            }
            persons.addAll(personsToAttribute);
            quizAsEntity.setPersons(persons);
            return saveQuiz(quizMapper.toQuizDTO(quizAsEntity));
        } else {
            return null;
        }
    }

}
