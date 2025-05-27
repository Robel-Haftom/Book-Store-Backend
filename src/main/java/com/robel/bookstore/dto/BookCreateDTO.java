package com.robel.bookstore.dto;

import com.robel.bookstore.entity.*;
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

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookTitle;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookAuthor;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookLanguage;

    @NotNull
    @Min(1)
    private Integer bookEdition;

    @Lob
    @Column(columnDefinition = "TEXT")
    @Size(max = 2000)
    private String bookDescription;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookPublisher;

    @NotNull
    private String bookPublicationDate;

    private String bookFormat;

    @NotNull
    @Min(0)
    private Double bookPrice;

    @NotNull
    @Min(0)
    private Integer stockQuantity;

    @NotNull
    @Min(0)
    private Integer pageNumber;

    @NotNull
    private Long categoryId;

}
