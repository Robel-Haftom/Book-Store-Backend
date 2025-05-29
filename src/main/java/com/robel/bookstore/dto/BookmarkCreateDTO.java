package com.robel.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkCreateDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Book ID cannot be null")
    private Long bookId;
}
