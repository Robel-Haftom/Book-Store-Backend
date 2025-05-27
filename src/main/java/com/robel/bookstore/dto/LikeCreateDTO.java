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
public class LikeCreateDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
