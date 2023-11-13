package com.sda.student.repository;

import com.sda.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByCategoryId(long categoryId);

}
