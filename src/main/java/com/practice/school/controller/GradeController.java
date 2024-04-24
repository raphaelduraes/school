package com.practice.school.controller;

import com.practice.school.exception.GradeNotFoundException;
import com.practice.school.model.Grade;
import com.practice.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/grade")
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

    @GetMapping("/all/student/{id}")
    public ResponseEntity<List<Grade>> getGradesByStudentId(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Grade>>(gradeService.getGradesByStudentId(id), HttpStatus.OK);
        } catch(GradeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/subject/{id}")
    public ResponseEntity<List<Grade>> getGradesBySubjectId(@PathVariable Long id) {
        try {
            return new ResponseEntity<List<Grade>>(gradeService.getGradesBySubjectId(id), HttpStatus.OK);
        } catch(GradeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        return new ResponseEntity<Grade>(gradeService.createGrade(grade), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long id) {
        try {
            return new ResponseEntity<Grade>(gradeService.updateGrade(id, grade), HttpStatus.OK);
        } catch(GradeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return new ResponseEntity<Grade>(HttpStatus.OK);
    }
}
