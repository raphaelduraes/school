package com.practice.school.repository;

import com.practice.school.model.Course;
import com.practice.school.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findByCourseSet_Id(Long courseId);
}
