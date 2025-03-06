package com.example.teachergroupapi.repository;

import com.example.teachergroupapi.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {}