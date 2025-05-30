package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.ReviewCreateDTO;
import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.entity.Review;

import java.time.format.DateTimeFormatter;

public class ReviewMapper {

    public static Review mapToReview(ReviewCreateDTO reviewCreateDTO){
        return Review.builder()
                .rating(reviewCreateDTO.getRating())
                .comment(reviewCreateDTO.getComment())
                .build();
    }

    public static ReviewResponseDTO mapToReviewResponseDTO(Review review){
        return new ReviewResponseDTO(
                review.getReviewId(),
                review.getRating().toString(),
                review.getComment(),
                review.getUser().getUserName(),
                review.getBook().getBookTitle(),
                review.getCreatedAt().format(DateTimeFormatter.ofPattern("MMM/dd/yyyy hh-mm-ss"))
        );
    }
}
