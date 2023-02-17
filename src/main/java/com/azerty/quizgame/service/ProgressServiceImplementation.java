package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.ProgressDAO;
import com.azerty.quizgame.dto.ProgressDTO;
import com.azerty.quizgame.model.Progress;
import com.azerty.quizgame.utils.ProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImplementation implements ProgressService {

    @Autowired
    private ProgressDAO progressDAO;

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
            Progress progressAsModel = progressMapper.toProgress(progress);
            progressAsModel.setId(id);
            return progressMapper.toProgressDTO(progressDAO.save(progressAsModel));
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
    public List<ProgressDTO> getAllProgressesByInternId(Long id)  {
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
        ProgressDTO progress = progressMapper.toProgressDTO(progressDAO.findProgressByInternIdAndQuizId(internId, quizId));
        /*if (progress.isPresent()) {
            return progressMapper.toProgressDTO(progress.get());
        } else {
            return null;
        }*/
        return progress;
    }

}
