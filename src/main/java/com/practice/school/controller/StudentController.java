package com.practice.school.controller;

import com.practice.school.exception.StudentNotFoundException;
import com.practice.school.model.Student;
import com.practice.school.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
        } catch(StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<List<Student>>(studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudentsByCourseId(@RequestParam("course_id") Long courseId) {
        return new ResponseEntity<List<Student>>(studentService.getStudentsByCourseId(courseId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.createStudent(student), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student, @PathVariable Long id) {
        try {
            return new ResponseEntity<Student>(studentService.updateStudent(id, student), HttpStatus.OK);
        } catch(StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }
}
