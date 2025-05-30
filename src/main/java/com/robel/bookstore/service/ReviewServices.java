package com.robel.bookstore.service;

import com.robel.bookstore.dto.ReviewCreateDTO;
import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.entity.Review;

import java.util.List;

public interface ReviewServices {

    ReviewResponseDTO createReview(ReviewCreateDTO reviewCreateDTO);
    ReviewResponseDTO getReviewById(Long reviewId);
    List<ReviewResponseDTO> getAllReviewsByBookId(Long bookId);
    List<ReviewResponseDTO> getAllReviews();
    ReviewResponseDTO updateReview(Long reviewId, ReviewCreateDTO reviewCreateDTO);
    void deleteReview(Long reviewId);
}
