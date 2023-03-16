package com.azerty.quizgame.model.dto;

public class QuestionInQuizDTO {

    private Long id;
    private String wording;
    private int maxDurationInSeconds;
    private AnswerInQuizDTO[] answers;
    private Long numberOfQuestionsLeft;


    public QuestionInQuizDTO() {
    }

    public QuestionInQuizDTO(Long id, String wording, int maxDurationInSeconds, AnswerInQuizDTO[] answers, Long numberOfQuestionsLeft) {
        this.id = id;
        this.wording = wording;
        this.maxDurationInSeconds = maxDurationInSeconds;
        this.answers = answers;
        this.numberOfQuestionsLeft = numberOfQuestionsLeft;
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

    public AnswerInQuizDTO[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerInQuizDTO[] answers) {
        this.answers = answers;
    }

    public Long getNumberOfQuestionsLeft() {
        return numberOfQuestionsLeft;
    }

    public void setNumberOfQuestionsLeft(Long numberOfQuestionsLeft) {
        this.numberOfQuestionsLeft = numberOfQuestionsLeft;
    }

}
