package com.azerty.quizgame.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "admins")
public class Admin extends Person {

    public Admin() {
        this.role = Role.ADMIN;
    }

    public Admin(Long id, String username, String lastname, String firstname, String email, Role role) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = Role.ADMIN;
    }

}
