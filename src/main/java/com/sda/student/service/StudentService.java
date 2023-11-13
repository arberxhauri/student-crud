package com.sda.student.service;

import com.sda.student.dto.StudentDto;
import com.sda.student.entity.Category;
import com.sda.student.entity.Student;
import com.sda.student.mapper.StudentMapper;
import com.sda.student.repository.CategoryRepository;
import com.sda.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService {

    private CategoryRepository categoryRepository;
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentDto save(StudentDto studentDto) {
        Category existingCategory = categoryRepository.findById(studentDto.getCategoryId()).orElseThrow(() ->
                new RuntimeException("Category with id: " + studentDto.getCategoryId() + " not found"));

        Student student = studentMapper.mapToEntity(studentDto);
        student.setCategory(existingCategory);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.mapToDto(savedStudent);
    }

    public List<StudentDto> findAllByCategoryId(long categoryId) {
        List<Student> studentList = studentRepository.findByCategoryId(categoryId);

        //FOR EACH LOOP
//        List<StudentDto> studentDtoList = new ArrayList<>();
//        for (Student student : studentList){
//            studentDtoList.add(studentMapper.mapToDto(student));
//        }

        return studentList.stream().map(student -> studentMapper.mapToDto(student)).collect(Collectors.toList());
    }

    public StudentDto findById(long categoryId ,long studentId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category with id : " + categoryId + " not found"));

        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id : " + studentId + " not found"));

        if (!((existingCategory.getId()) == (existingStudent.getCategory().getId()))){
            throw new RuntimeException("Student with id: " + studentId + " does not have category id: " + categoryId);
        }

        return studentMapper.mapToDto(existingStudent);
    }

    public StudentDto update(StudentDto studentDto, long studentId) {
        Category existingCategory = categoryRepository.findById(studentDto.getCategoryId()).orElseThrow(() ->
                new RuntimeException("Category with id : " + studentDto.getCategoryId() + " not found"));

        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id: " + studentId + " was not found in the database."));

        existingStudent.setId(studentId);
        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setAge(studentDto.getAge());
        existingStudent.setCategory(existingCategory);
        Student savedStudent = studentRepository.save(existingStudent);
        return studentMapper.mapToDto(savedStudent);


        //OPTIONAL
//        Optional<Student> existingStudent = studentRepository.findById(studentId);
//        if (existingStudent.isPresent()) {
//            Student studentToUpdate = existingStudent.get();
//            studentToUpdate.setId(studentId);
//            studentToUpdate.setFirstName(studentDto.getFirstName());
//            studentToUpdate.setLastName(studentDto.getLastName());
//            studentToUpdate.setAge(studentDto.getAge());
//            Student savedStudent = studentRepository.save(studentToUpdate);
//            return studentMapper.mapToDto(savedStudent);
//        } else {
//            throw new RuntimeException("Student not found with ID: " + studentId);
//        }

    }
    public void delete(long categoryId, long studentId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category with id : " + categoryId + " not found"));

        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student with id: " + studentId + " was not found in the database."));
        if (!((existingCategory.getId()) == (existingStudent.getCategory().getId()))){
            throw new RuntimeException("Student with id: " + studentId + " does not have category id: " + categoryId);
        }
        studentRepository.delete(existingStudent);
    }


}
