package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.CountsDTO;
import org.springframework.stereotype.Component;

@Component
public class CountsMapper {

    public CountsDTO toCountsDTO(Long[] counts) {
        Long internCount;
        try {
            internCount = counts[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            internCount = 0L;
        }

        Long quizCount;
        try {
            quizCount = counts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            quizCount = 0L;
        }

        return new CountsDTO(internCount, quizCount);
    }

}
