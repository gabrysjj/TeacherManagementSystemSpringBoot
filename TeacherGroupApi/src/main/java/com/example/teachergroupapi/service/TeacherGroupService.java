package com.example.teachergroupapi.service;

import com.example.teachergroupapi.dto.*;
import com.example.teachergroupapi.model.*;
import com.example.teachergroupapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class TeacherGroupService {
    private final TeacherRepository teacherRepo;
    private final TeacherGroupRepository groupRepo;
    private final RatingRepository ratingRepo;


    public TeacherGroupService(TeacherRepository teacherRepo, TeacherGroupRepository groupRepo, RatingRepository ratingRepo) {
        this.teacherRepo = teacherRepo;
        this.groupRepo = groupRepo;
        this.ratingRepo = ratingRepo;
    }
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher teacher = TeacherDto.mapTeacherDtoToTeacher(teacherDto);
        Teacher savedTeacher = teacherRepo.save(teacher);
        return TeacherDto.mapTeacherToTeacherDto(savedTeacher);
    }
    public String generateTeachersCsv() {
        List<Teacher> teachers = teacherRepo.findAll();
        StringWriter csvWriter = new StringWriter();

        // Dodanie nagłówków do CSV
        csvWriter.append("ID,First Name,Last Name,Group\n");

        // Dodanie danych nauczycieli do CSV
        for (Teacher teacher : teachers) {
            csvWriter.append(String.valueOf(teacher.getId())).append(",")
                    .append(teacher.getFirstName()).append(",")
                    .append(teacher.getLastName()).append(",");

            if (teacher.getGroup() != null) {
                csvWriter.append(teacher.getGroup().getName());
            } else {
                csvWriter.append("No Group");
            }

            csvWriter.append("\n");
        }

        return csvWriter.toString();
    }
    public void deleteTeacher(Long id) {
        if (!teacherRepo.existsById(id)) {
            throw new RuntimeException("Nauczyciel o ID " + id + " nie został znaleziony");
        }
        teacherRepo.deleteById(id);
    }

    public List<TeacherGroupDto> getAllGroups() {
        List<TeacherGroup> groups = groupRepo.findAll();
        return groups.stream()
                .map(TeacherGroupDto::mapTeacherGroupToDto)
                .collect(Collectors.toList());
    }
    public TeacherGroupDto addGroup(TeacherGroupDto dto) {
        TeacherGroup group = TeacherGroupDto.mapDtoToTeacherGroup(dto);
        TeacherGroup savedGroup = groupRepo.save(group);
        return TeacherGroupDto.mapTeacherGroupToDto(savedGroup);
    }
    public void deleteGroup(Long id) {
        if (!groupRepo.existsById(id)) {
            throw new RuntimeException("Grupa o ID " + id + " nie została znaleziona");
        }
        groupRepo.deleteById(id);
    }
    public List<TeacherDto> getTeachersByGroupId(Long groupId) {
        // Znajdź grupę na podstawie ID
        TeacherGroup group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupa o ID " + groupId + " nie została znaleziona"));

        // Pobierz nauczycieli z grupy i zamapuj na DTO
        return group.getTeachers().stream()
                .map(TeacherDto::mapTeacherToTeacherDto)
                .collect(Collectors.toList());
    }
    public double getGroupFillPercentage(Long groupId) {
        // Znajdź grupę po ID
        TeacherGroup group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupa o ID " + groupId + " nie została znaleziona"));

        // Oblicz procent zapełnienia
        int currentSize = group.getTeachers().size();
        int maxSize = group.getMaxSize();

        if (maxSize == 0) {
            throw new RuntimeException("Maksymalny rozmiar grupy nie może być równy 0");
        }

        return (double) currentSize / maxSize * 100;
    }

    public RatingDto addRating(RatingDto dto) {
        if (dto.ratingValue() < 1 || dto.ratingValue() > 5) {
            throw new IllegalArgumentException("Ocena musi być w zakresie od 1 do 5");
        }

        TeacherGroup group = groupRepo.findById(dto.group().getId())
                .orElseThrow(() -> new EntityNotFoundException("Grupa o ID " + dto.group().getId() + " nie została znaleziona"));

        Rating rating = RatingDto.mapDtoToRating(dto, group);
        Rating savedRating = ratingRepo.save(rating);

        return RatingDto.mapRatingToDto(savedRating);
    }
}

