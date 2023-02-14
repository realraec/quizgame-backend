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

    public Intern(Long id, String username, String password, String lastname, String firstname, String email, String company, Role role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.company = company;
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
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
