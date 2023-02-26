package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Progress;
import com.azerty.quizgame.model.entity.Question;
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

    @Autowired
    private ProgressDAO progressDAO;

//    @Autowired
//    private RecordDAO recordDAO;

    private final ProgressMapper progressMapper = new ProgressMapper();


    @Override
    public List<ProgressDTO> getAllProgresses() {
        Iterator<Progress> progressIterator = progressDAO.findAll().iterator();
        List<ProgressDTO> progresses = new ArrayList<>();

        while (progressIterator.hasNext()) {
            progresses.add(progressMapper.toProgressDTO(progressIterator.next()));
        }

        if (!progresses.isEmpty()) {
            return progresses;
        } else {
            return null;
        }
    }

    @Override
    public ProgressDTO getProgressById(Long id) {
        Optional<Progress> progress = progressDAO.findById(id);
        if (progress.isPresent()) {
            return progressMapper.toProgressDTO(progress.get());
        } else {
            return null;
        }
    }

    @Override
    public ProgressDTO saveProgress(ProgressDTO progress) {
        return progressMapper.toProgressDTO(progressDAO.save(progressMapper.toProgress(progress)));
    }

    @Override
    public ProgressDTO updateProgressById(ProgressDTO progress, Long id) {
        Optional<Progress> checkProgress = progressDAO.findById(id);
        if (checkProgress.isPresent()) {
            Progress progressAsEntity = progressMapper.toProgress(progress);
            progressAsEntity.setId(id);
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
    public List<ProgressDTO> getAllProgressesByInternId(Long id) {
        Iterator<Progress> progressIterator = progressDAO.findAllProgressesByInternId(id).iterator();
        List<ProgressDTO> progresses = new ArrayList<>();
        while (progressIterator.hasNext()) {
            progresses.add(progressMapper.toProgressDTO(progressIterator.next()));
        }

        if (!progresses.isEmpty()) {
            return progresses;
        } else {
            return null;
        }
    }

    @Override
    public ProgressDTO getProgressByInternIdAndQuizId(Long internId, Long quizId) {
        Progress progress = progressDAO.findProgressByInternIdAndQuizId(internId, quizId);
        if (progress != null) {
            return progressMapper.toProgressDTO(progress);
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
                progressMapper.toProgressDTO(progressDAO.save(progressAsEntity));
            }

            List<Question> questions = progressAsEntity.getQuiz().getQuestions();
            if (record.getQuestionId() == questions.get(questions.size() - 1).getId()) {
                progressAsEntity.setDateAndTimeOfCompletion(LocalDateTime.now());
                progressMapper.toProgressDTO(progressDAO.save(progressAsEntity));
            }
            return true;
        } else {
            return false;
        }
    }

}
