package com.azerty.quizgame.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "interns")
public class Intern extends Person {

    private String company;

    public Intern() {
        this.role = Role.INTERN;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public String toString() {
        return "Intern{" +
                "company='" + company + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
