package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.JourneyDAO;
import com.azerty.quizgame.dto.JourneyDTO;
import com.azerty.quizgame.model.Journey;
import com.azerty.quizgame.utils.JourneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JourneyServiceImplementation implements JourneyService {

    @Autowired
    private JourneyDAO journeyDAO;

    private final JourneyMapper journeyMapper = new JourneyMapper();


    @Override
    public List<JourneyDTO> getAllJourneys() {
        Iterator<Journey> journeyIterator = journeyDAO.findAll().iterator();
        List<JourneyDTO> journeys = new ArrayList<>();

        while (journeyIterator.hasNext()) {
            journeys.add(journeyMapper.toJourneyDTO(journeyIterator.next()));
        }

        if (!journeys.isEmpty()) {
            return journeys;
        } else {
            return null;
        }
    }

    @Override
    public JourneyDTO getJourneyById(Long id) {
        Optional<Journey> journey = journeyDAO.findById(id);
        if (journey.isPresent()) {
            return journeyMapper.toJourneyDTO(journey.get());
        } else {
            return null;
        }
    }

    @Override
    public JourneyDTO saveJourney(JourneyDTO journey) {
        return journeyMapper.toJourneyDTO(journeyDAO.save(journeyMapper.toJourney(journey)));
    }

    @Override
    public JourneyDTO updateJourneyById(JourneyDTO journey, Long id) {
        Optional<Journey> checkJourney = journeyDAO.findById(id);
        if (checkJourney.isPresent()) {
            Journey journeyAsModel = journeyMapper.toJourney(journey);
            journeyAsModel.setId(id);
            return journeyMapper.toJourneyDTO(journeyDAO.save(journeyAsModel));
        } else {
            return null;
        }
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

}
