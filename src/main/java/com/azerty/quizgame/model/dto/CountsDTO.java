package com.azerty.quizgame.model.dto;

public class CountsDTO {

    private Long internCount;
    private Long quizCount;


    public CountsDTO(Long internCount, Long quizCount) {
        this.internCount = internCount;
        this.quizCount = quizCount;
    }


    public Long getInternCount() {
        return internCount;
    }

    public void setInternCount(Long internCount) {
        this.internCount = internCount;
    }

    public Long getQuizCount() {
        return quizCount;
    }

    public void setQuizCount(Long quizCount) {
        this.quizCount = quizCount;
    }

}
