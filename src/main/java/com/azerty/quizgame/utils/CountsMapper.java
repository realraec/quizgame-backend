package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.CountsDTO;
import org.springframework.stereotype.Component;

@Component
public class CountsMapper {

    public CountsDTO toCountsDTO(Long[] counts) {
        Long internCount = counts[0];
        Long quizCount = counts[1];

        return new CountsDTO(internCount, quizCount);
    }

}
