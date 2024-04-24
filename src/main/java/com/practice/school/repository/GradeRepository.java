package com.practice.school.repository;

import com.practice.school.model.Grade;
import com.practice.school.model.Student;
import com.practice.school.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    Iterable<Grade> findByStudentId(Long studentId);

    Iterable<Grade> findBySubjectId(Long subjectId);
}
