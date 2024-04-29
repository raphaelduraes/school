package com.practice.school.controller;

import com.practice.school.exception.SubjectNotFoundException;
import com.practice.school.model.Subject;
import com.practice.school.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.OK);
        } catch(SubjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getSubjects() {
        try {
            return new ResponseEntity<>(subjectService.getSubjects(), HttpStatus.OK);
        } catch(SubjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Set<Subject>> getSubjectsByCourseId(@RequestParam("course_id") Long courseId) {
        return new ResponseEntity<>(subjectService.getSubjectsByCourseId(courseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return new ResponseEntity<>(subjectService.createSubject(subject), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateCourse(@RequestBody Subject subject, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(subjectService.updateSubject(id, subject), HttpStatus.OK);
        } catch(SubjectNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
