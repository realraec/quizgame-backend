package com.azerty.quizgame.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
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

    //@JsonIgnore
    //@OneToMany

    //@JoinColumn(name = "fk_progress"
    //, referencedColumnName="DATREG_META_CODE"
    //)
    //private List<Answer> answers;
    //@JoinTable(name = "question_success")
//    ,
//    joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
//    inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    //@MapKey(name = "itemName")

    @OneToMany
    @JoinColumn(name = "fk_progress")
    private List<Record> records;



    public Progress() {
    }

    public Progress(Long id, LocalDateTime dateAndTimeOfCompletion, int durationInSeconds, int score, Intern intern, Quiz quiz, List<Record> records) {
        this.dateAndTimeOfCompletion = dateAndTimeOfCompletion;
        this.durationInSeconds = durationInSeconds;
        this.score = score;
        this.intern = intern;
        this.quiz = quiz;
        this.records = records;
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
