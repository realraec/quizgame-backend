package com.azerty.quizgame.model.dto;

public class QuestionDTO {

    private Long id;
    private String wording;
    private int maxDurationInSeconds;
    private Long quizId;
    private Long[] answersIds;


    public QuestionDTO(Long id, String wording, int maxDurationInSeconds, Long quizId, Long[] answersIds) {
        this.id = id;
        this.wording = wording;
        this.maxDurationInSeconds = maxDurationInSeconds;
        this.quizId = quizId;
        this.answersIds = answersIds;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public int getMaxDurationInSeconds() {
        return maxDurationInSeconds;
    }

    public void setMaxDurationInSeconds(int maxDurationInSeconds) {
        this.maxDurationInSeconds = maxDurationInSeconds;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long[] getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(Long[] answersIds) {
        this.answersIds = answersIds;
    }

}
