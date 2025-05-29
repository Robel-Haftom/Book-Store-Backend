package com.robel.bookstore.controller.admin;

import com.robel.bookstore.dto.BookCreateDTO;
import com.robel.bookstore.dto.BookResponseDTO;
import com.robel.bookstore.service.BookServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/books")
public class AdminBookController {

    @Autowired
    private BookServices bookServices;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping()
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestPart("book") BookCreateDTO bookCreateDTO,
                                                   @RequestPart("file") List<MultipartFile> allImg) throws IOException {
        return ResponseEntity.ok().body(bookServices.addBook(bookCreateDTO, allImg));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long bookId,
                                                      @Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return ResponseEntity.ok().body(bookServices.updateBook(bookId, bookCreateDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) throws IOException {
        bookServices.deleteBook(bookId);
        return ResponseEntity.ok().body("Book deleted successfully");
    }

}
