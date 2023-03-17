package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.dao.RecordDAO;
import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.model.entity.*;
import com.azerty.quizgame.utils.ProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImplementation implements ProgressService {

    private final ProgressDAO progressDAO;
    private final PersonDAO personDAO;
    private final QuizDAO quizDAO;
    private final RecordDAO recordDAO;
    private final ProgressMapper progressMapper = new ProgressMapper();


    @Autowired
    public ProgressServiceImplementation(ProgressDAO progressDAO, PersonDAO personDAO, QuizDAO quizDAO,
                                         RecordDAO recordDAO) {
        this.progressDAO = progressDAO;
        this.personDAO = personDAO;
        this.quizDAO = quizDAO;
        this.recordDAO = recordDAO;
    }


    @Override
    public List<ProgressDTO> getAllProgresses() {
        try {
            Iterator<Progress> progressIterator = progressDAO.findAll().iterator();
            List<ProgressDTO> progresses = new ArrayList<>();
            while (progressIterator.hasNext()) {
                progresses.add(progressMapper.toProgressDTO(progressIterator.next()));
            }
            return progresses;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public ProgressDTO getProgressById(Long id) {
        Optional<Progress> progress = progressDAO.findById(id);
        return progress.map(progressMapper::toProgressDTO).orElse(null);
    }

    @Override
    public ProgressDTO saveProgress(ProgressDTO progress) {
        Long[] recordsIds = progress.getRecordsIds();
        if (recordsIds != null && recordsIds.length > 0) {
            for (int i = 0; i < recordsIds.length; i++) {
                Optional<Record> checkRecord = recordDAO.findById(recordsIds[i]);
                if (checkRecord.isEmpty()) {
                    return null;
                }
            }
        } else {
            progress.setRecordsIds(new Long[]{});
        }

        Optional<Person> checkPerson = personDAO.findById(progress.getPersonId());
        Optional<Quiz> checkQuiz = quizDAO.findById(progress.getQuizId());
        if (checkPerson.isPresent() && checkQuiz.isPresent()) {
            Progress progressAsEntity = progressMapper.toProgress(progress);
            progressAsEntity.setId(progress.getId());
            return progressMapper.toProgressDTO(progressDAO.save(progressAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProgressById(Long id) {
        Optional<Progress> checkProgress = progressDAO.findById(id);
        if (checkProgress.isPresent()) {
            progressDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProgressDTO updateProgressById(ProgressDTO progress, Long id) {
        Optional<Progress> checkProgress = progressDAO.findById(id);
        if (checkProgress.isPresent()) {
            Progress progressAsEntity = progressMapper.toProgress(progress);
            progressAsEntity.setId(id);
            return saveProgress(progressMapper.toProgressDTO(progressAsEntity));
        } else {
            return null;
        }
    }

    @Override
    public List<ProgressDTO> getAllProgressesByPersonId(Long personId) {
        Optional<Person> checkPerson = personDAO.findById(personId);
        if (checkPerson.isPresent()) {
            Iterator<Progress> progressIterator = progressDAO.findAllProgressesByPersonId(personId).iterator();
            List<ProgressDTO> progresses = new ArrayList<>();
            while (progressIterator.hasNext()) {
                progresses.add(progressMapper.toProgressDTO(progressIterator.next()));
            }
            return progresses;
        } else {
            return null;
        }
    }

    @Override
    public ProgressDTO getProgressByPersonIdAndQuizId(Long personId, Long quizId) {
        Optional<Person> checkPerson = personDAO.findById(personId);
        Optional<Quiz> checkQuiz = quizDAO.findById(quizId);
        if (checkPerson.isPresent() && checkQuiz.isPresent()) {
            Optional<Progress> progress = progressDAO.findProgressByPersonIdAndQuizId(personId, quizId);
            return progress.map(progressMapper::toProgressDTO).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public boolean updateProgressDependingOnRecord(RecordDTO record) {
        Optional<Progress> checkProgress = progressDAO.findById(record.getProgressId());
        if (checkProgress.isPresent()) {
            Progress progressAsEntity = checkProgress.get();
            if (record.isSuccess()) {
                progressAsEntity.setScore(progressAsEntity.getScore() + 1);
                saveProgress(progressMapper.toProgressDTO(progressAsEntity));
            }
            List<Question> questions = progressAsEntity.getQuiz().getQuestions();
            if (record.getQuestionId() == questions.get(questions.size() - 1).getId()) {
                progressAsEntity.setDateAndTimeOfCompletion(LocalDateTime.now());
                saveProgress(progressMapper.toProgressDTO(progressAsEntity));
            }
            return true;
        } else {
            return false;
        }
    }

}
