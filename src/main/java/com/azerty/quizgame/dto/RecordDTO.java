package com.azerty.quizgame.dto;

public class RecordDTO {

    private Long id;

    private Long questionId;
    private boolean isSuccess;

    public RecordDTO() {
    }

    public RecordDTO(Long id, Long questionId, boolean isSuccess) {
        this.id = id;
        this.questionId = questionId;
        this.isSuccess = isSuccess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

}
