package com.azerty.quizgame.model.dto;

public class RecordWithPickedAnswersDTO {

    private Long questionId;
    private Long progressId;
    private Long[] pickedAnswersIds;


    public RecordWithPickedAnswersDTO() {
    }

    public RecordWithPickedAnswersDTO(Long questionId, Long progressId, Long[] pickedAnswersIds) {
        this.questionId = questionId;
        this.progressId = progressId;
        this.pickedAnswersIds = pickedAnswersIds;
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

    public Long[] getPickedAnswersIds() {
        return pickedAnswersIds;
    }

    public void setPickedAnswersIds(Long[] pickedAnswersIds) {
        this.pickedAnswersIds = pickedAnswersIds;
    }

}
