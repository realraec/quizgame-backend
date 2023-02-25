package com.azerty.quizgame.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "progresses")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_progress")
    private Long id;

    private LocalDateTime dateAndTimeOfCompletion;
    private int durationInSeconds;
    private int score;

    @ManyToOne
    @JoinColumn(name = "fk_intern")
    private Intern intern;

    @ManyToOne
    @JoinColumn(name = "fk_quiz")
    private Quiz quiz;

    @JsonIgnore
    @OneToMany(mappedBy = "progress")
    private List<Record> records;


    public Progress() {
    }

    public Progress(Long id, LocalDateTime dateAndTimeOfCompletion, int durationInSeconds, int score, Intern intern, Quiz quiz, List<Record> records) {
        this.dateAndTimeOfCompletion = dateAndTimeOfCompletion;
        this.durationInSeconds = durationInSeconds;
        this.score = score;
        this.intern = intern;
        this.quiz = quiz;
        this.records = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTimeOfCompletion() {
        return dateAndTimeOfCompletion;
    }

    public void setDateAndTimeOfCompletion(LocalDateTime dateAndTime) {
        this.dateAndTimeOfCompletion = dateAndTime;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

//    public boolean addRecord(Record record) {
//        if (!this.records.contains(record)) {
//            this.records.add(record);
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", dateAndTimeOfCompletion=" + dateAndTimeOfCompletion +
                ", durationInSeconds=" + durationInSeconds +
                ", score=" + score +
                ", intern=" + intern +
                ", quiz=" + quiz +
                ", records=" + records +
                '}';
    }

}
