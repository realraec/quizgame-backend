package com.azerty.quizgame.model.dto;

public class RecordDTO {

    private Long id;
    private boolean isSuccess;
    private Long questionId;
    private Long progressId;


    public RecordDTO() {
    }

    public RecordDTO(Long id, boolean isSuccess, Long questionId, Long progressId) {
        this.id = id;
        this.isSuccess = isSuccess;
        this.questionId = questionId;
        this.progressId = progressId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

}
