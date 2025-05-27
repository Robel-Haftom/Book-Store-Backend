package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.CategoryCreateDTO;
import com.robel.bookstore.dto.CategoryResponseDTO;
import com.robel.bookstore.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryCreateDTO categoryCreateDTO){
        return Category.builder()
                .categoryName(categoryCreateDTO.getCategoryName())
                .build();
    }

    public static CategoryResponseDTO mapToCategoryResponseDTO(Category category){
        return new CategoryResponseDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getBookList().size()
        );
    }
}
