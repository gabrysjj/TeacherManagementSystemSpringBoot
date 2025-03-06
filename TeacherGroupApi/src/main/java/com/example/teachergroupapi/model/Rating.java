package com.example.teachergroupapi.model;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ratingValue;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private TeacherGroup group;

    public Rating() {}
    public Rating(int ratingValue, TeacherGroup group) {
        this.ratingValue = ratingValue;
        this.group = group;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getValue() {
        return ratingValue;
    }
    public void setValue(int rating_value) {
        this.ratingValue = rating_value;
    }
    public TeacherGroup getGroup() {
        return group;
    }
    public void setGroup(TeacherGroup group) {
        this.group = group;
    }
}