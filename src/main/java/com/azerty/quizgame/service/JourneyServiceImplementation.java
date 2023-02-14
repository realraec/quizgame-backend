package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.JourneyDAO;
import com.azerty.quizgame.model.Journey;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JourneyServiceImplementation implements JourneyService {

    @Autowired
    private JourneyDAO journeyDAO;

    @Override
    public List<Journey> getAllJourneys() {
        List<Journey> admins = (List<Journey>) journeyDAO.findAll();

        if (!admins.isEmpty()) {
            return admins;
        } else {
            return null;
        }
    }

    @Override
    public Journey getJourneyById(Long id) {
        Optional<Journey> journey = journeyDAO.findById(id);
        return journey.orElse(null);
    }

    @Override
    public Journey saveJourney(Journey journey) {
        return journeyDAO.save(journey);
    }

    @Override
    public boolean deleteJourneyById(Long id) {
        Optional<Journey> checkJourney = journeyDAO.findById(id);
        if (checkJourney.isPresent()) {
            journeyDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Journey updateJourneyById(Journey journey, Long id) {
        Optional<Journey> checkJourney = journeyDAO.findById(id);
        if (checkJourney.isPresent()) {
            journey.setId(id);
            return journeyDAO.save(journey);
        } else {
            return null;
        }
    }
}
