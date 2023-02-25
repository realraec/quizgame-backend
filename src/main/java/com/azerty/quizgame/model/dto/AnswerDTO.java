package com.azerty.quizgame.model.dto;

public class AnswerDTO {

    private Long id;
    private String wording;
    private boolean isCorrect;
    private Long questionId;


    public AnswerDTO(Long id, String wording, boolean isCorrect, Long questionId) {
        this.id = id;
        this.wording = wording;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

}
