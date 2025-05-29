package com.robel.bookstore.controller.publicRoutes;

import com.robel.bookstore.dto.CategoryResponseDTO;
import com.robel.bookstore.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/public/categories")
public class PublicCategoryController {

    @Autowired
    private CategoryServices categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(categoryId));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }
}
