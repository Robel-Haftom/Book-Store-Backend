package com.robel.bookstore.service;

import com.robel.bookstore.dto.CategoryCreateDTO;
import com.robel.bookstore.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryServices {
    CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO updateCategory(Long id, CategoryCreateDTO categoryCreateDTO);

    void deleteCategory(Long id);
}
