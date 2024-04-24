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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "professor")
    private String professor;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Grade> gradeSet;

    @ManyToMany(mappedBy = "subjectSet", cascade = CascadeType.ALL)
    private Set<Course> courseSet;
}
