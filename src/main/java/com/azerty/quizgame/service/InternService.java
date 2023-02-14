package com.azerty.quizgame.service;

import com.azerty.quizgame.dto.InternDTO;

import java.util.List;

public interface InternService {

    List<InternDTO> getAllInterns() throws Exception;

    InternDTO getInternById(Long id) throws Exception;

    InternDTO saveIntern(InternDTO intern) throws Exception;

    boolean deleteInternById(Long id) throws Exception;

    InternDTO updateInternById(InternDTO intern, Long id) throws Exception;

}
