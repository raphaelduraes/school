package com.practice.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.school.validation.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name cannot be blank")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Address cannot be blank")
    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Email(message = "Please enter a valid email address")
    @Column(name = "email")
    private String email;

    @NonNull
    @Phone(message = "Invalid Phone Format")
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Grade> gradeSet;

    @ManyToMany(mappedBy = "studentSet", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Course> courseSet;
}
