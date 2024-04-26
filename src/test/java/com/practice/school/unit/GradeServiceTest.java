package com.practice.school.unit;

import com.practice.school.exception.GradeNotFoundException;
import com.practice.school.model.Grade;
import com.practice.school.model.Student;
import com.practice.school.model.Subject;
import com.practice.school.repository.GradeRepository;
import com.practice.school.service.GradeService;
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
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepository;
    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradeByIdTest() {
        Grade gradeSample = getGradeSample();
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(gradeSample));

        Grade result = gradeService.getGradeById(1L);
        assertEquals(result, gradeSample);
    }

    @Test
    public void getGradeByIdTest_throwsGradeNotFoundException() {
        assertThrows(GradeNotFoundException.class, () -> gradeService.getGradeById(1L));
    }

    @Test
    public void getGradeByCourseIdTest() {
        List<Grade> expected = getGradesSample();
        when(gradeRepository.findByStudentId(1L)).thenReturn(expected);
        List<Grade> result = gradeService.getGradesByStudentId(1L);
        assertSame(result, expected);
    }

    @Test
    public void createGradeTest() {
        Grade student = getGradeSample();
        when(gradeRepository.save(student)).thenReturn(student);

        Grade result = gradeService.createGrade(student);
        assertEquals(result, student);
    }

    @Test
    public void updateGradeTest() {
        Grade existingGrade = getGradeSample();
        existingGrade.setId(1L);

        Student raphael = new Student("Raphael Duraes", "5 Francis Street", "raphael.duraes@gmail.com", "+35387601432");
        raphael.setId(1L);
        Subject englishSubject = new Subject("english", "John Sykes");
        englishSubject.setId(2L);

        Grade updatedGrade = new Grade("A", englishSubject, raphael);
        updatedGrade.setId(1L);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(existingGrade));
        when(gradeRepository.save(updatedGrade)).thenReturn(updatedGrade);

        Grade result = gradeService.updateGrade(1L, updatedGrade);
        assertEquals(result, updatedGrade);
    }

    @Test
    public void updateGradeTest_throwsGradeNotFoundException() {
        Grade updatedGrade = getGradeSample();
        updatedGrade.setId(1L);
        when(gradeRepository.findById(1L)).thenThrow(new GradeNotFoundException());
        assertThrows(GradeNotFoundException.class, () -> gradeService.updateGrade(1L, updatedGrade));
    }

    private Grade getGradeSample() {
        Subject mathSubject = new Subject("math", "Joe Doe");
        mathSubject.setId(1L);
        Student raphael = new Student("Raphael Duraes", "5 Francis Street", "raphael.duraes@gmail.com", "+35387601432");
        raphael.setId(1L);

        return new Grade("A+", mathSubject, raphael);
    }

    private List<Grade> getGradesSample() {

        Student raphael = new Student("Raphael Duraes", "5 Francis Street", "raphael.duraes@gmail.com", "+35387601432");
        raphael.setId(1L);
        Student janice = new Student("Janice Griffin", "98 O'Connell Street", "janice@gmail.com", "+35382344532");
        janice.setId(2L);

        Subject mathSubject = new Subject("math", "Joe Doe");
        mathSubject.setId(1L);

        Subject englishSubject = new Subject("english", "John Sykes");
        englishSubject.setId(2L);

        Subject programmingSubject = new Subject("Programming", "Mario");
        mathSubject.setId(3L);

        Subject graphSubject = new Subject("Programming", "Mario");
        mathSubject.setId(3L);

        return List.of(
                new Grade("A+", mathSubject, raphael),
                new Grade("B", englishSubject, raphael),
                new Grade("B+", programmingSubject, janice),
                new Grade("B", graphSubject, janice)
        );
    }
}
