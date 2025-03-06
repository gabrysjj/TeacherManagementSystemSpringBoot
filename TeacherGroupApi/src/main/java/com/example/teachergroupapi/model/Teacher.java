package com.example.teachergroupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private TeacherGroup group;

    public Teacher() {}
    public Teacher(String firstName, String lastName, TeacherGroup group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public TeacherGroup getGroup() {
        return group;
    }
    public void setGroup(TeacherGroup group) {
        this.group = group;
    }
}
