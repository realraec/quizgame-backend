package com.azerty.quizgame.service;

import com.azerty.quizgame.dto.JourneyDTO;

import java.util.List;

public interface JourneyService {

    List<JourneyDTO> getAllJourneys() throws Exception;

    JourneyDTO getJourneyById(Long id) throws Exception;

    JourneyDTO saveJourney(JourneyDTO journey) throws Exception;

    boolean deleteJourneyById(Long id) throws Exception;

    JourneyDTO updateJourneyById(JourneyDTO journey, Long id) throws Exception;

}
