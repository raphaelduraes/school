package com.practice.school.repository;

import com.practice.school.model.Course;
import com.practice.school.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Set<Subject> findAllByCourse(Course course);
}
