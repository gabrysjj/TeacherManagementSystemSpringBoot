package com.example.teachergroupapi.controller;

import com.example.teachergroupapi.dto.*;
import com.example.teachergroupapi.service.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TeacherGroupController {
    private final TeacherGroupService service;

    public TeacherGroupController(TeacherGroupService service) {
        this.service = service;
    }

    @PostMapping("/teacher")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(service.addTeacher(teacherDto));
    }
    @GetMapping("/teacher/csv")
    public ResponseEntity<String> getTeachersCsv() {
        String csvContent = service.generateTeachersCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=teachers.csv")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csvContent);
    }
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/group")
    public ResponseEntity<List<TeacherGroupDto>> getAllGroups() {
        List<TeacherGroupDto> groups = service.getAllGroups();
        return ResponseEntity.ok(groups);
    }
    @PostMapping("/group")
    public ResponseEntity<TeacherGroupDto> addGroup(@RequestBody TeacherGroupDto dto) {
        TeacherGroupDto savedGroup = service.addGroup(dto);
        return ResponseEntity.ok(savedGroup);
    }
    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        service.deleteGroup(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    @GetMapping("/group/{id}/teacher")
    public ResponseEntity<List<TeacherDto>> getTeachersByGroup(@PathVariable Long id) {
        List<TeacherDto> teachers = service.getTeachersByGroupId(id);
        return ResponseEntity.ok(teachers);
    }
    @GetMapping("/group/{id}/fill")
    public ResponseEntity<Double> getGroupFillPercentage(@PathVariable Long id) {
        double fillPercentage = service.getGroupFillPercentage(id);
        return ResponseEntity.ok(fillPercentage);
    }

    @PostMapping("/rating")
    public ResponseEntity<RatingDto> addRating(@RequestBody RatingDto dto) {
        RatingDto savedRating = service.addRating(dto);
        return ResponseEntity.ok(savedRating);
    }
}
