package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.ReviewCreateDTO;
import com.robel.bookstore.dto.ReviewResponseDTO;
import com.robel.bookstore.entity.Review;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.exception.BookNotFoundException;
import com.robel.bookstore.exception.ReviewNotFoundException;
import com.robel.bookstore.exception.UserNotFoundException;
import com.robel.bookstore.mapper.ReviewMapper;
import com.robel.bookstore.repository.BookRepository;
import com.robel.bookstore.repository.ReviewRepository;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.service.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewServicesImpl implements ReviewServices {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewCreateDTO reviewCreateDTO) {
        User user = userRepository.findById(reviewCreateDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + reviewCreateDTO.getUserId()));
        Book book = bookRepository.findById(reviewCreateDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("No book found with this id: " + reviewCreateDTO.getBookId()));

        Review review = ReviewMapper.mapToReview(reviewCreateDTO);
        review.setCreatedAt(LocalDateTime.now());
        review.setBook(book);
        review.setUser(user);
        review.setUpdatedAt(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        return ReviewMapper.mapToReviewResponseDTO(savedReview);
    }

    @Override
    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("No review found with this id: " + reviewId));
        return ReviewMapper.mapToReviewResponseDTO(review);
    }

    @Override
    public List<ReviewResponseDTO> getAllReviewsByBookId(Long bookId) {
        List<Review> reviews = reviewRepository.findByBook_BookId(bookId);

        return reviews.stream().map(ReviewMapper::mapToReviewResponseDTO).toList();
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll() ;

        return reviews.stream().map(ReviewMapper::mapToReviewResponseDTO).toList();

    }

    @Override
    public ReviewResponseDTO updateReview(Long reviewId, ReviewCreateDTO reviewCreateDTO) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("No review found with this id: " + reviewId));
        boolean updated = false;
        if(Objects.nonNull(reviewCreateDTO.getRating()) && !"".equalsIgnoreCase(reviewCreateDTO.getRating().toString())){
            existingReview.setRating(reviewCreateDTO.getRating());
            updated = true;
        }
        if(Objects.nonNull(reviewCreateDTO.getComment()) && !"".equalsIgnoreCase(reviewCreateDTO.getComment())){
            existingReview.setComment(reviewCreateDTO.getComment());
            updated = true;
        }

        if(updated){
            existingReview.setUpdatedAt(LocalDateTime.now());
            Review updatedReview = reviewRepository.save(existingReview);
            return ReviewMapper.mapToReviewResponseDTO(updatedReview);
        }

        return ReviewMapper.mapToReviewResponseDTO(existingReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("No review found with this id: " + reviewId));
        reviewRepository.deleteById(reviewId);
    }
}
