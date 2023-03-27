package com.azerty.quizgame.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_quiz")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    private List<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "quizzes_persons",
            joinColumns = {@JoinColumn(name = "pk_quiz")},
            inverseJoinColumns = {@JoinColumn(name = "pk_person")}
    )
    private List<Person> persons;


    public Quiz() {
        this.questions = new ArrayList<>();
        this.persons = new ArrayList<>();
    }

    public Quiz(Long id, String title, String summary, List<Question> questions, List<Person> persons) {
        this.title = title;
        this.summary = summary;
        this.questions = questions;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                //", questions=" + questions +
                //", persons=" + persons +
                '}';
    }
}
