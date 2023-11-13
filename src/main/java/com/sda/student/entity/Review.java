package com.sda.student.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "Description must not be empty")
    @Size(min = 5, max = 1050, message = "Description must be between 5 and 1050")
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}
