package com.azerty.quizgame.model.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountsDTOTests {

    @Test
    public void shouldGetAndSetAllAttributesForCountsDTO() {
        Long internCount = 15L;
        Long quizCount = 5L;

        CountsDTO countsDTO = new CountsDTO();
        countsDTO.setInternCount(internCount);
        countsDTO.setQuizCount(quizCount);

        Assertions.assertEquals(internCount, countsDTO.getInternCount());
        Assertions.assertEquals(quizCount, countsDTO.getQuizCount());
    }

}
