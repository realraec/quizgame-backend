package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.InternDAO;
import com.azerty.quizgame.dto.InternDTO;
import com.azerty.quizgame.model.Intern;
import com.azerty.quizgame.utils.InternMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class InternServiceImplementation implements InternService {

    @Autowired
    private InternDAO internDAO;

    private final InternMapper internMapper = new InternMapper();


    @Override
    public List<InternDTO> getAllInterns() {
        Iterator<Intern> internIterator = internDAO.findAll().iterator();
        List<InternDTO> interns = new ArrayList<>();
        while (internIterator.hasNext()) {
            interns.add(internMapper.toInternDTO(internIterator.next()));
        }

        if (!interns.isEmpty()) {
            return interns;
        } else {
            return null;
        }
    }

    @Override
    public InternDTO getInternById(Long id) {
        Optional<Intern> intern = internDAO.findById(id);
        if (intern.isPresent()) {
            return internMapper.toInternDTO(intern.get());
        } else {
            return null;
        }
    }

    @Override
    public InternDTO saveIntern(InternDTO intern) {
        return internMapper.toInternDTO(internDAO.save(internMapper.toIntern(intern)));
    }

    @Override
    public InternDTO updateInternById(InternDTO intern, Long id) {
        Optional<Intern> checkIntern = internDAO.findById(id);
        if (checkIntern.isPresent()) {
            Intern internAsModel = internMapper.toIntern(intern);
            internAsModel.setId(id);
            return internMapper.toInternDTO(internDAO.save(internAsModel));
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteInternById(Long id) {
        Optional<Intern> checkIntern = internDAO.findById(id);
        if (checkIntern.isPresent()) {
            internDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
