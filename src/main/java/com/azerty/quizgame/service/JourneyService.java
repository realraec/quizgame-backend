package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Journey;

import java.util.List;

public interface JourneyService {

    List<Journey> getAllJourneys() throws Exception;

    Journey getJourneyById(Long id) throws Exception;

    Journey saveJourney(Journey journey) throws Exception;

    boolean deleteJourneyById(Long id) throws Exception;

    Journey updateJourneyById(Journey journey, Long id) throws Exception;

}
