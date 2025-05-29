package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.BookmarkCreateDTO;
import com.robel.bookstore.dto.BookmarkResponseDTO;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.entity.Bookmark;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.exception.BookNotFoundException;
import com.robel.bookstore.exception.BookmarkExistException;
import com.robel.bookstore.exception.BookmarkNotFoundException;
import com.robel.bookstore.exception.UserNotFoundException;
import com.robel.bookstore.mapper.BookmarkMapper;
import com.robel.bookstore.repository.BookRepository;
import com.robel.bookstore.repository.BookmarkRepository;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.service.BookmarkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookmarkServicesImpl implements BookmarkServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;


    @Override
    public BookmarkResponseDTO addBookmark(BookmarkCreateDTO bookmarkCreateDTO) {
        User user = userRepository.findById(bookmarkCreateDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + bookmarkCreateDTO.getUserId()));
        Book book = bookRepository.findById(bookmarkCreateDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("No book found with this id: " + bookmarkCreateDTO.getBookId()));

        bookmarkRepository.findByUser_UserIdAndBook_BookId(bookmarkCreateDTO.getUserId(), bookmarkCreateDTO.getBookId())
                .ifPresent((bookmark) -> {
                    throw new BookmarkExistException("Book si already in the bookmark list");
                });
        Bookmark bookmark = Bookmark.builder()
                .createdAt(LocalDateTime.now())
                .user(user)
                .book(book)
                .build();

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return BookmarkMapper.mapToBookmarkResponseDTO(savedBookmark);
    }

    @Override
    public void removeBookmark(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new BookmarkNotFoundException("No bookmark found with this id: " + bookmarkId));
        bookmarkRepository.deleteById(bookmarkId);
    }
}
