package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "interns")
public class Intern extends Person {

    private String company;

    @ManyToMany(mappedBy = "interns", fetch = FetchType.EAGER)
    private List<Quiz> quizzes;


    public Intern() {
        this.role = Role.INTERN;
    }

    public Intern(Long id, String username, String password, String lastname, String firstname, String email, String company, Role role, List<Quiz> quizzes) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.company = company;
        this.role = Role.INTERN;
        this.quizzes = new ArrayList<>();
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }


    @Override
    public String toString() {
        return "Intern{" +
                "company='" + company + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
