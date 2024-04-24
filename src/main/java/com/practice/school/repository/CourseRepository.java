package com.practice.school.repository;

import com.practice.school.model.Course;
import com.practice.school.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findCoursesByStudent(Student student);
}
