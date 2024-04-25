package com.practice.school.unit;

import com.practice.school.exception.CourseNotFoundException;
import com.practice.school.model.Course;
import com.practice.school.model.Course.Shift;
import com.practice.school.repository.CourseRepository;
import com.practice.school.service.CourseService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseService courseService;

    @Test
    public void getCourseByIdTest() {
        Course courseMockValue = new Course();
        courseMockValue.setId(1L);
        courseMockValue.setName("Math");
        courseMockValue.setShift(Shift.AFTERNOON);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(courseMockValue));

        Course result = courseService.getCourseById(1L);
        assertEquals(result, courseMockValue);
    }

    @Test
    public void getCourseByIdTest_throwsCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(1L));
    }

    @Test
    public void getCoursesByStudentIdTest() {
        List<Course> expected = List.of(
                new Course("System Information", Shift.EVENING),
                new Course("Cyber Security", Shift.AFTERNOON)
        );
        Long studentId = 1L;
        when(courseRepository.findByStudentSet_id(studentId)).thenReturn(expected);

        List<Course> result = courseService.getCoursesByStudentId(1L);
        assertEquals(result, expected);
    }

    @Test
    public void getCoursesByStudentIdTest_empty() {
        List<Course> result = courseService.getCoursesByStudentId(1L);
        assertTrue(result.isEmpty());
    }

    @Test
    public void createCourseTest() {
        Course course = new Course("Vet", Shift.MORNING);
        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.createCourse(course);
        assertEquals(result, course);
    }

    @Test
    public void updateCourseTest() {
        Course existingCourse = new Course("Medicine",Shift.MORNING);
        existingCourse.setId(1L);

        Course updatedCourse = new Course("Medicine",Shift.AFTERNOON);
        existingCourse.setId(1L);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);

        Course result = courseService.updateCourse(1L, updatedCourse);
        assertEquals(result, updatedCourse);
    }

    @Test
    public void updateCourseTest_throwsCourseNotFoundException() {
        Course updatedCourse = new Course("Medicine",Shift.AFTERNOON);
        when(courseRepository.findById(1L)).thenThrow(new CourseNotFoundException());
        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse(1L, updatedCourse));
    }
}
