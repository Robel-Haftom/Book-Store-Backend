package com.robel.bookstore.controller;

import com.robel.bookstore.dto.CategoryCreateDTO;
import com.robel.bookstore.dto.CategoryResponseDTO;
import com.robel.bookstore.service.CategoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseEntity.ok().body(categoryService.createCategory(categoryCreateDTO));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                              @Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryId, categoryCreateDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().body("Category deleted successfully");
    }

}
