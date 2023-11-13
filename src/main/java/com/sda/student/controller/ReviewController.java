package com.sda.student.controller;

import com.sda.student.dto.ReviewDto;
import com.sda.student.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/save/{studentId}")
    public ReviewDto save(@Valid @RequestBody ReviewDto reviewDto, @PathVariable long studentId){
        return reviewService.save(reviewDto, studentId);
    }

    @GetMapping("/{studentId}")
    public List<ReviewDto> findAllByStudentId(@PathVariable("studentId") long studentId){
        return reviewService.findAllByStudentId(studentId);
    }

    @GetMapping("/{studentId}/{reviewId}")
    public ResponseEntity<ReviewDto> findById(@PathVariable("studentId") long studentId,
                                              @PathVariable("reviewId") long reviewId) {
        return ResponseEntity.ok(reviewService.findById(studentId,reviewId));
    }

    @PutMapping("/update/{studentId}/{reviewId}")
    public ResponseEntity<ReviewDto> update(@PathVariable("studentId") long studentId,
                                            @PathVariable("reviewId") long reviewId,
                                            @RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.update(studentId,reviewId,reviewDto));
    }

    @DeleteMapping("/delete/{studentId}/{reviewId}")
    public void delete(@PathVariable("studentId") long studentId,
                       @PathVariable("reviewId") long reviewId){
        reviewService.delete(studentId, reviewId);
    }
}
