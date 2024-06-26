package com.practice.school.controller;

import com.practice.school.exception.GradeNotFoundException;
import com.practice.school.model.Grade;
import com.practice.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Grade>(gradeService.getGradeById(id), HttpStatus.OK);
        } catch(GradeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", params = "student_id")
    public ResponseEntity<List<Grade>> getGradesByStudentId(@RequestParam("student_id") Long studentId) {
        return new ResponseEntity<>(gradeService.getGradesByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "", params = "subject_id")
    public ResponseEntity<List<Grade>> getGradesBySubjectId(@RequestParam("subject_id") Long subjectId) {
        return new ResponseEntity<>(gradeService.getGradesBySubjectId(subjectId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.createGrade(grade), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(gradeService.updateGrade(id, grade), HttpStatus.OK);
        } catch(GradeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
