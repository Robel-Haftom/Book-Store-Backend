package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.BookCreateDTO;
import com.robel.bookstore.dto.BookResponseDTO;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.enums.BookFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookMapper {

    public static Book mapToBook(BookCreateDTO bookCreateDTO){
        return Book.builder()
                .bookTitle(bookCreateDTO.getBookTitle())
                .bookAuthor(bookCreateDTO.getBookAuthor())
                .bookFormat(BookFormat.valueOf(bookCreateDTO.getBookFormat().toUpperCase()))
                .bookLanguage(bookCreateDTO.getBookLanguage())
                .bookDescription(bookCreateDTO.getBookDescription())
                .bookPublisher(bookCreateDTO.getBookPublisher())
                .bookPublicationDate(LocalDate.parse(bookCreateDTO.getBookPublicationDate()))
                .bookPrice(bookCreateDTO.getBookPrice())
                .pageNumber(bookCreateDTO.getPageNumber())
                .stockQuantity(bookCreateDTO.getStockQuantity())
                .build();
    }

    public static BookResponseDTO mapToBookResponseDTO(Book book){
        return new BookResponseDTO(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookDescription(),
                book.getBookCategory().getCategoryName(),
                book.getBookLanguage(),
                book.getPageNumber().toString(),
                book.getBookEdition(),
                book.getBookCoverImgUrl(),
                book.getAllImagesUrl(),
                book.getBookPublisher(),
                book.getBookPublicationDate(),
                book.getBookFormat().toString(),
                book.getBookPrice().toString(),
                book.getStockQuantity().toString(),
                book.getAverageRating().toString(),
                book.getBookReviews().stream().map(ReviewMapper::mapToReviewResponseDTO).toList(),
                book.getLikes().stream().map(LikeMapper::mapToLikeResponseDTO).toList(),
                book.getBookmarks().stream().map(BookmarkMapper::mapToBookmarkResponseDTO).toList()
        );
    }
}
