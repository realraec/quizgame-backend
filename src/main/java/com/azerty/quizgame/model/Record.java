package com.azerty.quizgame.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pk_record", nullable = false)
    private Long id;

    @Column(nullable = false)
    private int timeTakenInSeconds;

    @Column(nullable = false)
    private boolean isSuccess;

    @ManyToOne
    @JoinColumn(name = "fk_question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "fk_progress")
    private Progress progress;


    public Record() {
    }

    public Record(Long id, int timeTakenInSeconds, boolean isSuccess, Question question, Progress progress) {
        this.timeTakenInSeconds = timeTakenInSeconds;
        this.isSuccess = isSuccess;
        this.question = question;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimeTakenInSeconds() {
        return timeTakenInSeconds;
    }

    public void setTimeTakenInSeconds(int timeTakenInSeconds) {
        this.timeTakenInSeconds = timeTakenInSeconds;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", timeTakenInSeconds=" + timeTakenInSeconds +
                ", isSuccess=" + isSuccess +
                ", question=" + question +
                ", progress=" + progress +
                '}';
    }

}
