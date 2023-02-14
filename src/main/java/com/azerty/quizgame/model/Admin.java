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
}
