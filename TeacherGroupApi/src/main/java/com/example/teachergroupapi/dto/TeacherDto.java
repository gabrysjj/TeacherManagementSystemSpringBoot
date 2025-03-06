package com.example.teachergroupapi.dto;

import com.example.teachergroupapi.model.Teacher;
import com.example.teachergroupapi.model.TeacherGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record TeacherDto(
        Long id,
        String firstName,
        String lastName,
        @JsonIgnore
        TeacherGroup group
) {
    public static TeacherDto mapTeacherToTeacherDto(Teacher teacher) {
        return new TeacherDto(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getGroup());
    }
    public static Teacher mapTeacherDtoToTeacher(TeacherDto teacherDto) {
        return new Teacher(teacherDto.firstName, teacherDto.lastName, teacherDto.group);
    }
}
