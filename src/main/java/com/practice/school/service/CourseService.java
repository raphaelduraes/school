package com.practice.school.service;

import com.practice.school.exception.CourseNotFoundException;
import com.practice.school.model.Course;
import com.practice.school.model.Student;
import com.practice.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course getCourseById(Long id) throws CourseNotFoundException{
        return courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
    }

    public Set<Course> getCoursesByStudent(Student student) {
        return (Set<Course>) courseRepository.findCoursesByStudent(student);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long studentId, Course updatedCourse) throws CourseNotFoundException {
        Course courseToBeUpdated =  courseRepository.findById(studentId).orElseThrow(CourseNotFoundException::new);
        updatedCourse.setId(studentId);

        return courseRepository.save(updatedCourse);
    }

    public void deleteSubject(Long id) {
        courseRepository.deleteById(id);
    }
}
