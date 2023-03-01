package com.azerty.quizgame.model.dto;

public class QuestionInQuizDTO {

    private Long id;
    private int maxDurationInSeconds;
    private String wording;
    private AnswerInQuizDTO[] answers;


    public QuestionInQuizDTO() {
    }

    public QuestionInQuizDTO(Long id, int maxDurationInSeconds, String wording, AnswerInQuizDTO[] answers) {
        this.id = id;
        this.maxDurationInSeconds = maxDurationInSeconds;
        this.wording = wording;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxDurationInSeconds() {
        return maxDurationInSeconds;
    }

    public void setMaxDurationInSeconds(int maxDurationInSeconds) {
        this.maxDurationInSeconds = maxDurationInSeconds;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public AnswerInQuizDTO[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerInQuizDTO[] answers) {
        this.answers = answers;
    }

}
