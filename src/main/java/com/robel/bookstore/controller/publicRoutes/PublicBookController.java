package com.robel.bookstore.controller.publicRoutes;

import com.robel.bookstore.dto.BookResponseDTO;
import com.robel.bookstore.service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/public/books")
public class PublicBookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(bookServices.getBookById(bookId));
    }

    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok().body(bookServices.getAllBooks());
    }

}
