package com.practice.school.unit;

import com.practice.school.exception.SubjectNotFoundException;
import com.practice.school.model.Subject;
import com.practice.school.repository.SubjectRepository;
import com.practice.school.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectService subjectService;

    @Test
    public void getSubjectByIdTest() {
        Subject subjectSample = getSubjectSample();
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subjectSample));

        Subject result = subjectService.getSubjectById(1L);
        assertEquals(result, subjectSample);
    }

    @Test
    public void getSubjectByIdTest_throwsSubjectNotFoundException() {
        assertThrows(SubjectNotFoundException.class, () -> subjectService.getSubjectById(1L));
    }

    @Test
    public void getSubjectByCourseIdTest() {
        Set<Subject> expected = getSubjectsSample();
        when(subjectRepository.findByCourseSet_Id(1L)).thenReturn(expected);
        Set<Subject> result = subjectService.getSubjectsByCourseId(1L);
        assertSame(result, expected);
    }

    @Test
    public void createSubjectTest() {
        Subject student = getSubjectSample();
        when(subjectRepository.save(student)).thenReturn(student);

        Subject result = subjectService.createSubject(student);
        assertEquals(result, student);
    }

    @Test
    public void updateSubjectTest() {
        Subject existingStudent = getSubjectSample();
        existingStudent.setId(1L);

        Subject updatedSubject = new Subject("math", "Joe Doe");
        updatedSubject.setId(1L);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(subjectRepository.save(updatedSubject)).thenReturn(updatedSubject);

        Subject result = subjectService.updateSubject(1L, updatedSubject);
        assertEquals(result, updatedSubject);
    }

    @Test
    public void updateSubjectTest_throwsCourseNotFoundException() {
        Subject updatedStudent = getSubjectSample();
        updatedStudent.setId(1L);
        when(subjectRepository.findById(1L)).thenThrow(new SubjectNotFoundException());
        assertThrows(SubjectNotFoundException.class, () -> subjectService.updateSubject(1L, updatedStudent));
    }

    private Subject getSubjectSample() {
        return new Subject("Math", "Martin Byrne");
    }

    private Set<Subject> getSubjectsSample() {
        return Set.of(
                new Subject("Math", "Martin Byrne"),
                new Subject("Science", "Dan Hashmann"),
                new Subject("Immunology", "John Doe")
        );
    }
}
