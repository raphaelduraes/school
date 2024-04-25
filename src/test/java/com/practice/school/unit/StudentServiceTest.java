package com.practice.school.unit;

import com.practice.school.exception.StudentNotFoundException;
import com.practice.school.model.Student;
import com.practice.school.repository.StudentRepository;
import com.practice.school.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    public void getStudentByIdTest() {
        Student studentSample = getStudentSample();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentSample));

        Student result = studentService.getStudentById(1L);
        assertEquals(result, studentSample);
    }

    @Test
    public void getStudentByIdTest_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    public void getStudentsByCourseIdTest() {
        List<Student> expected = getStudentsSample();
        when(studentRepository.findByCourseSet_Id(1L)).thenReturn(expected);
        List<Student> result = studentService.getStudentsByCourseId(1L);
        assertSame(result, expected);
    }

    @Test
    public void createStudentTest() {
        Student student = getStudentSample();
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.createStudent(student);
        assertEquals(result, student);
    }

    @Test
    public void updateCourseTest() {
        Student existingStudent = getStudentSample();
        existingStudent.setId(1L);

        Student updatedStudent = new Student("Edward Glenn", "5 Francis Street, Dublin 8", "raphael.duraes@yahoo.ie", "+35387601432");
        updatedStudent.setId(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);
        assertEquals(result, updatedStudent);
    }

    @Test
    public void updateCourseTest_throwsCourseNotFoundException() {
        Student updatedStudent = getStudentSample();
        updatedStudent.setId(1L);
        when(studentRepository.findById(1L)).thenThrow(new StudentNotFoundException());
        assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(1L, updatedStudent));
    }

    private Student getStudentSample() {
        return new Student(
                "Raphael Duraes",
                "5 Francis Street",
                "raphael.duraes@gmail.com",
                "+35387601432"
        );
    }

    private List<Student> getStudentsSample() {
        return List.of(
                new Student("Raphael Duraes", "5 Francis Street", "raphael.duraes@gmail.com", "+35387601432"),
                new Student("Janice Griffin", "98 O'Connell Street", "janice@gmail.com", "+35382344532"),
                new Student("Henry Colleman", "2a Harcourt Street", "henry.coleman@gmail.com", "+3532323432")
        );
    }
}
