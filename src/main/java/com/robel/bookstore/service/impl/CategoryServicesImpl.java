package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.CategoryCreateDTO;
import com.robel.bookstore.dto.CategoryResponseDTO;
import com.robel.bookstore.entity.Category;
import com.robel.bookstore.exception.CategoryExistException;
import com.robel.bookstore.exception.CategoryNotFoundException;
import com.robel.bookstore.mapper.CategoryMapper;
import com.robel.bookstore.repository.CategoryRepository;
import com.robel.bookstore.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServicesImpl implements CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        categoryRepository.findByCategoryName(categoryCreateDTO.getCategoryName())
                .ifPresent(category -> {
                    throw new CategoryExistException("Category already exists");
                });

        Category category = CategoryMapper.mapToCategory(categoryCreateDTO);
        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return CategoryMapper.mapToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::mapToCategoryResponseDTO).toList();
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryCreateDTO categoryCreateDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        if(Objects.nonNull(categoryCreateDTO.getCategoryName()) && !"".equals(categoryCreateDTO.getCategoryName())){
            categoryRepository.findByCategoryName(categoryCreateDTO.getCategoryName())
                    .ifPresent(existingCategory -> {
                        throw new CategoryExistException("Category already exists");
                    });
        }
        category.setCategoryName(categoryCreateDTO.getCategoryName());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryResponseDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }
}
