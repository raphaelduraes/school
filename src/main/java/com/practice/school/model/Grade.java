package com.practice.school.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "score", nullable = false)
    private String score;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
}
