package com.practice.school.service;

import com.practice.school.exception.SubjectNotFoundException;
import com.practice.school.model.Subject;
import com.practice.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject getSubjectById(Long id) throws SubjectNotFoundException{
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    public List<Subject> getSubjects() {
        return (List<Subject>) subjectRepository.findAll();
    }

    public Set<Subject> getSubjectsByCourseId(Long courseId) {
        return subjectRepository.findByCourseSet_Id(courseId);
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long subjectId, Subject updatedSubject) throws SubjectNotFoundException {
        subjectRepository.findById(subjectId).orElseThrow(SubjectNotFoundException::new);
        updatedSubject.setId(subjectId);

        return subjectRepository.save(updatedSubject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
