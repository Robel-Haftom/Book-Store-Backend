package com.robel.bookstore.controller.admin;

import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.service.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/reviews")
public class AdminReviewController {

    @Autowired
    private ReviewServices reviewServices;

    @GetMapping()
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(){
        return ResponseEntity.ok().body(reviewServices.getAllReviews());
    }
}
