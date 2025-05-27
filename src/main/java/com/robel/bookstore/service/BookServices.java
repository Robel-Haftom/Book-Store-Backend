package com.robel.bookstore.service;


import com.robel.bookstore.dto.BookCreateDTO;
import com.robel.bookstore.dto.BookResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookServices {
    BookResponseDTO addBook(BookCreateDTO bookCreateDTO, List<MultipartFile> allImg) throws IOException;

    BookResponseDTO getBookById(Long bookId);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO updateBook(Long bookId, BookCreateDTO bookCreateDTO);

    void deleteBook(int bookId) throws IOException;
}
