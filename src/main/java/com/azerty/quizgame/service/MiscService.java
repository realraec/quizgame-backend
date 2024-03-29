package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.CountsDTO;

public interface MiscService {

    CountsDTO getInternCountAndQuizCount() throws Exception;

    boolean clearDatabase() throws Exception;

    boolean resetDatabase() throws Exception;

}
