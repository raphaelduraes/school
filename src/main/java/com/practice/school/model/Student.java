package com.practice.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Grade> gradeSet;

    @ManyToMany(mappedBy = "studentSet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Course> courseSet;
}
