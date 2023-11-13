package com.sda.student.mapper;

import com.sda.student.dto.CategoryDto;
import com.sda.student.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {

    public Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(categoryDto.getId());

        return category;
    }

    public  CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setId(category.getId());

        return categoryDto;
    }
}
