package com.sda.student.controller;

import com.sda.student.dto.StudentDto;
import com.sda.student.entity.Student;
import com.sda.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

      private StudentService studentService;

    @PostMapping("/save")
    public StudentDto save(@Valid @RequestBody StudentDto studentDto) {
       return studentService.save(studentDto);
    }

    @GetMapping("/{categoryId}")
    public List<StudentDto> findByCategoryId(@PathVariable(name = "categoryId") long categoryId) {
        return studentService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/{categoryId}/{studentId}")
    public StudentDto findById(@PathVariable(name = "categoryId") long categoryId, @PathVariable(name = "studentId") Long studentId) {
        return studentService.findById(categoryId, studentId);
    }

    @PutMapping("/{studentId}")
    public StudentDto update(@RequestBody StudentDto studentDto,
                             @PathVariable(name = "studentId") Long studentId) {
        return studentService.update(studentDto, studentId);
    }


    @DeleteMapping("/{categoryId}/{studentId}")
    public String delete(@PathVariable(name = "categoryId") long categoryId,
                         @PathVariable(name = "studentId") long studentId) {
        studentService.delete(categoryId, studentId);
        return "Student successfully deleted!";
    }


}
