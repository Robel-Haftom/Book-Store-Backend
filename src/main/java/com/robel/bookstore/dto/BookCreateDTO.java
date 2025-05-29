package com.robel.bookstore.dto;

import com.robel.bookstore.entity.*;
import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDTO {

    @NotBlank(message = "Book title cannot be blank", groups = {OnCreate.class})
    @Size(min = 1, max = 255, message = "Book title must be between 1 and 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String bookTitle;

    @NotBlank(message = "Book author cannot be blank", groups = {OnCreate.class})
    @Size(min = 1, max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String bookAuthor;

    @NotBlank(message = "Book ISBN cannot be blank", groups = {OnCreate.class})
    @Size(min = 1, max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String bookLanguage;

    @NotNull(message = "Book edition cannot be null", groups = {OnCreate.class})
    @Min(value = 1, message = "Book edition must be at least 1", groups = {OnCreate.class, OnUpdate.class})
    private Integer bookEdition;

    @NotBlank(message = "Book description cannot be blank", groups = {OnCreate.class})
    @Size(max = 2000, message = "Book description must be less than 2000 characters", groups = {OnCreate.class, OnUpdate.class})
    private String bookDescription;

    @NotBlank(message = "Book publisher cannot be blank", groups = {OnCreate.class})
    @Size(min = 1, max = 255, message = "Book publisher must be between 1 and 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String bookPublisher;

    @NotNull(message = "Book publication date cannot be null", groups = {OnCreate.class})
    private String bookPublicationDate;

    @NotBlank(message = "Boo format cannot be blank", groups = {OnCreate.class})
    private String bookFormat;

    @NotNull(message = "Book price cannot be null", groups = {OnCreate.class})
    @Min(value = 0, message = "Book price must be at least 0", groups = {OnCreate.class, OnUpdate.class})
    private Double bookPrice;

    @NotNull(message = "Book discount cannot be null", groups = {OnCreate.class})
    @Min(value = 0, message = "Book discount must be at least 0", groups = {OnCreate.class, OnUpdate.class})
    private Integer stockQuantity;

    @NotNull(message = "Book stock quantity cannot be null", groups = {OnCreate.class})
    @Min(value = 0, message = "Book stock quantity must be at least 0", groups = {OnCreate.class, OnUpdate.class})
    private Integer pageNumber;

    @NotNull(message = "Book weight cannot be null", groups = {OnCreate.class})
    private Long categoryId;

}
