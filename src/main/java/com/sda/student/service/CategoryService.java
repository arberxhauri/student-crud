package com.sda.student.service;

import com.sda.student.dto.CategoryDto;
import com.sda.student.entity.Category;
import com.sda.student.mapper.CategoryMapper;
import com.sda.student.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    public CategoryDto save(CategoryDto categoryDto){
        Category savedCategory = categoryRepository.save(categoryMapper.mapToEntity(categoryDto));

        return categoryMapper.mapToDto(savedCategory);
    }

    public List<CategoryDto> findAll(){
        List<Category> allCategory = categoryRepository.findAll();

        return allCategory.stream().map(category -> categoryMapper.mapToDto(category))
                .collect(Collectors.toList());
    }

        public CategoryDto findById(long categoryId){
            Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                    new RuntimeException("Category with id: " + categoryId + " not found"));

            return categoryMapper.mapToDto(existingCategory);
        }

        public CategoryDto update(CategoryDto categoryDto, long categoryId) {
            Category category = categoryMapper.mapToEntity(categoryDto);
            category.setId(categoryId);
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());

            Category savedCategory = categoryRepository.save(category);

            return categoryMapper.mapToDto(category);
        }

        public void delete(long categoryId){
            Category category = new Category();
            category.setId(categoryId);
            categoryRepository.delete(category);
        }
    }
