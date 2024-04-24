package com.practice.school.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    enum Shift{MORNING, AFTERNOON, EVENING}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "shift", nullable = false)
    @Enumerated(EnumType.STRING)
    private Shift shift;

    @ManyToMany
    @JoinTable(
            name = "courses_subjects",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjectSet;

    @ManyToMany
    @JoinTable(
            name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> studentSet;
}
