package com.robel.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateDTO {

    @NotBlank(message = "Category name cannot be blank")
    @Size(min = 3, max=50, message = "Category name must be between 3 and 50 characters")
    private String categoryName;
}
