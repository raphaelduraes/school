package com.practice.school.service;

import com.practice.school.exception.StudentNotFoundException;
import com.practice.school.model.Course;
import com.practice.school.model.Student;
import com.practice.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public Student getStudentById(Long id) throws StudentNotFoundException{
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Set<Student> getStudentsByCourse(Course course) {
        return (Set<Student>) studentRepository.findAllByCourse(course);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, Student updatedStudent) throws StudentNotFoundException {
        Student studentToBeUpdated =  studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        updatedStudent.setId(studentId);

        return studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
