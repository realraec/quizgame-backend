package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.CountsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountsMapper {

    public CountsDTO toCountsDTO(List<Long> resultList) {
        Long internCount;
        internCount = resultList.get(0);

        Long quizCount;
        quizCount = resultList.get(1);

        return new CountsDTO(internCount, quizCount);
    }

}
