package com.example.teachergroupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class TeacherGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int maxSize;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers;


    public TeacherGroup() {
    }
    public TeacherGroup(String name, int maxSize) {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
