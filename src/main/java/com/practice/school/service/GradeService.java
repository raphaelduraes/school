package com.practice.school.service;

import com.practice.school.exception.GradeNotFoundException;
import com.practice.school.model.Grade;
import com.practice.school.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade getGradeById(Long id) throws GradeNotFoundException{
        return gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        return (List<Grade>) gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGradesBySubjectId(Long subjectId) {
        return (List<Grade>) gradeRepository.findBySubjectId(subjectId);
    }

    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Long gradeId, Grade updatedGrade) throws GradeNotFoundException {
        Grade gradeToBeUpdated =  gradeRepository.findById(gradeId).orElseThrow(GradeNotFoundException::new);
        updatedGrade.setId(gradeId);

        return gradeRepository.save(updatedGrade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
