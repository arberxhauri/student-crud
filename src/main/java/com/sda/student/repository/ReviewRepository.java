package com.sda.student.repository;


import com.sda.student.dto.ReviewDto;
import com.sda.student.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStudentId(long id);
}
