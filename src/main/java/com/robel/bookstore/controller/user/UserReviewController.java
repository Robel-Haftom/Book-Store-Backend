package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.ReviewCreateDTO;
import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.service.ReviewServices;
import com.robel.bookstore.validation.OnCreate;
import com.robel.bookstore.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/reviews")
public class UserReviewController {

    @Autowired
    private ReviewServices reviewServices;

    @PostMapping()
    public ResponseEntity<ReviewResponseDTO> createReview(@Validated(OnCreate.class) @RequestBody ReviewCreateDTO reviewCreateDTO){
        return ResponseEntity.ok().body(reviewServices.createReview(reviewCreateDTO));
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok().body(reviewServices.getReviewById(reviewId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(@PathVariable("bookId") Long bookId){
        return ResponseEntity.ok().body(reviewServices.getAllReviewsByBookId(bookId));
    }

    @PutMapping("{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable("reviewId") Long reviewId,
                                                          @Validated(OnUpdate.class) @RequestBody ReviewCreateDTO reviewCreateDTO){
        return ResponseEntity.ok().body(reviewServices.updateReview(reviewId, reviewCreateDTO));
    }

}
