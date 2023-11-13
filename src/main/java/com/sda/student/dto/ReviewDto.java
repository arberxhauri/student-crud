package com.sda.student.dto;

import com.sda.student.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50")
    private String name;

    @NotEmpty(message = "Description must not be empty")
    @Size(min = 5, max = 1050, message = "Description must be between 5 and 1050")
    private String description;

    private StudentDto studentDto;
}
