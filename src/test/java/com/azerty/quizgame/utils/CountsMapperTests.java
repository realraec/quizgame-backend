package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.CountsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CountsMapperTests {

    private final CountsMapper countsMapper = new CountsMapper();

    @Test
    public void shouldGoFromListToCountsDTO() {
        Long internCount = 15L;
        Long quizCount = 5L;
        List<Long> counts = new ArrayList<>();
        counts.add(internCount);
        counts.add(quizCount);

        CountsDTO countsDTO = countsMapper.toCountsDTO(counts);
        CountsDTO countsDTO2 = new CountsDTO(internCount, quizCount);

        Assertions.assertEquals(internCount, countsDTO.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO.getQuizCount());
        Assertions.assertEquals(internCount, countsDTO2.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO2.getQuizCount());
    }

    @Test
    public void shouldGoFromListToCountsDTOEmpty() {
        Long internCount = 15L;
        Long quizCount = 5L;
        List<Long> counts = new ArrayList<>();
        counts.add(internCount);
        counts.add(quizCount);

        CountsDTO countsDTO = countsMapper.toCountsDTO(counts);
        CountsDTO countsDTO2 = new CountsDTO(internCount, quizCount);

        Assertions.assertEquals(internCount, countsDTO.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO.getQuizCount());
        Assertions.assertEquals(internCount, countsDTO2.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO2.getQuizCount());
    }

}
