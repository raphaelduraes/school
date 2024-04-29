package com.practice.school.controller;

import com.practice.school.exception.CourseNotFoundException;
import com.practice.school.exception.StudentNotFoundException;
import com.practice.school.model.Course;
import com.practice.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        try {
            return new ResponseEntity<List<Course>>(courseService.getCourses(), HttpStatus.OK);
        } catch(StudentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCoursesByStudentId(@RequestParam("student_id") Long studentId) {
        return new ResponseEntity<List<Course>>(courseService.getCoursesByStudentId(studentId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        try {
            return new ResponseEntity<Course>(courseService.getCourseById(id), HttpStatus.OK);
        } catch(CourseNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<Course>(courseService.createCourse(course), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        try {
            return new ResponseEntity<Course>(courseService.updateCourse(id, course), HttpStatus.OK);
        } catch(CourseNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        courseService.deleteSubject(id);
        return new ResponseEntity<Course>(HttpStatus.OK);
    }
}
