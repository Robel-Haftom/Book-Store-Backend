package com.robel.bookstore.controller.common;

import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.service.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/common/reviews")
public class ReviewController {

    @Autowired
    private ReviewServices reviewServices;


    @GetMapping("{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok().body(reviewServices.getReviewById(reviewId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(@PathVariable("bookId") Long bookId){
        return ResponseEntity.ok().body(reviewServices.getAllReviewsByBookId(bookId));
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId){
        reviewServices.deleteReview(reviewId);
        return ResponseEntity.ok().body("Review deleted successfully");
    }

}
