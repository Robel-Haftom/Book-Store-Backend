package com.robel.bookstore.dto;


import java.time.LocalDate;
import java.util.List;

public record BookResponseDTO (
        Long bookId,
        String bookTitle,
        String bookAuthor,
        String bookDescription,
        String categoryName,
        String bookLanguage,
        String bookPageNumber,
        String bookEdition,
        String bookCoverImgUrl,
        List<String> allImagesUrl,
        String bookPublisher,
        LocalDate bookPublicationDate,
        String bookFormat,
        String bookPrice,
        String stockQuantity,
        String averageRating,
        List<ReviewResponseDTO> reviews,
        List<LikeResponseDTO> likes,
        List<BookmarkResponseDTO> bookmarks
){
}
