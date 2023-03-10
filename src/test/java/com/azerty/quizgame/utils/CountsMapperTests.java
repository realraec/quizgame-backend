package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.CountsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountsMapperTests {

    private final CountsMapper countsMapper = new CountsMapper();

    @Test
    public void shouldGoFromArrayToCountsDTO() {
        Long internCount = 15L;
        Long quizCount = 5L;
        Long[] counts = {internCount, quizCount};

        CountsDTO countsDTO = countsMapper.toCountsDTO(counts);
        CountsDTO countsDTO2 = new CountsDTO(internCount, quizCount);

        Assertions.assertEquals(internCount, countsDTO.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO.getQuizCount());
        Assertions.assertEquals(internCount, countsDTO2.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO2.getQuizCount());
    }

}
