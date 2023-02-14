package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.InternDAO;
import com.azerty.quizgame.model.Intern;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class InternServiceImplementation implements InternService {

    @Autowired
    private InternDAO internDAO;

    @Override
    public List<Intern> getAllInterns() {
        List<Intern> interns = (List<Intern>) internDAO.findAll();

        if (!interns.isEmpty()) {
            return interns;
        } else {
            return null;
        }
    }

    @Override
    public Intern getInternById(Long id) {
        Optional<Intern> intern = internDAO.findById(id);
        return intern.orElse(null);
    }

    @Override
    public Intern saveIntern(Intern intern) {
        return internDAO.save(intern);
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

    @Override
    public Intern updateInternById(Intern intern, Long id) {
        Optional<Intern> checkIntern = internDAO.findById(id);
        if (checkIntern.isPresent()) {
            intern.setId(id);
            return internDAO.save(intern);
        } else {
            return null;
        }
    }

}
