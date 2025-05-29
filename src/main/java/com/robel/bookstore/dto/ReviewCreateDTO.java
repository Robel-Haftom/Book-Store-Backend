package com.robel.bookstore.dto;

import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateDTO {

    @NotNull(message = "Rating cannot be null", groups = {OnCreate.class})
    @Min(value = 1, message = "Rating must be at least 1", groups = {OnCreate.class})
    private Integer rating;

    @NotBlank(message = "Comment cannot be blank", groups = {OnCreate.class})
    @Size(max = 2000, message = "Comment cannot be more than 2000 characters")
    private String comment;

    @NotNull(message = "Book ID cannot be null", groups = {OnCreate.class})
    Long bookId;

    @NotNull(message = "User ID cannot be null", groups = {OnCreate.class}) 
    Long userId;

}
