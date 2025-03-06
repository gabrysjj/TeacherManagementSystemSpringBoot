package com.example.teachergroupapi.dto;
import com.example.teachergroupapi.model.Teacher;
import com.example.teachergroupapi.model.TeacherGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record TeacherGroupDto(
    Long id,
    String name,
    int maxSize,
    List<TeacherDto> teachers
){
    public static TeacherGroupDto mapTeacherGroupToDto(TeacherGroup group) {
        TeacherGroupDto dto = new TeacherGroupDto(group.getId(), group.getName(), group.getMaxSize(), new ArrayList<>());
        if (group.getTeachers() != null) {
            group.getTeachers().forEach(teacher -> dto.teachers.add(TeacherDto.mapTeacherToTeacherDto(teacher)));
        }

        return dto;
    }

    public static TeacherGroup mapDtoToTeacherGroup(TeacherGroupDto dto) {
        TeacherGroup group = new TeacherGroup();
        group.setId(dto.id());
        group.setName(dto.name());
        group.setMaxSize(dto.maxSize());
        // Teachers są mapowane osobno, zależnie od logiki
        return group;
    }
}
