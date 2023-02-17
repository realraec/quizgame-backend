package com.azerty.quizgame.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;
    private boolean isSuccess;

    public Record() {
    }

    public Record(Long id, Question question, boolean isSuccess) {
        this.id = id;
        this.question = question;
        this.isSuccess = isSuccess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", question=" + question +
                ", isSuccess=" + isSuccess +
                '}';
    }

}
