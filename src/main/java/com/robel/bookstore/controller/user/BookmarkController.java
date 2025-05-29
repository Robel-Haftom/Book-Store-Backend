package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.BookmarkCreateDTO;
import com.robel.bookstore.dto.BookmarkResponseDTO;
import com.robel.bookstore.service.BookmarkServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkServices bookmarkServices;

    @PostMapping()
    public ResponseEntity<BookmarkResponseDTO> addBookmark(@Valid @RequestBody BookmarkCreateDTO bookmarkCreateDTO){
        return ResponseEntity.ok().body(bookmarkServices.addBookmark(bookmarkCreateDTO));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<String> removeBookmark(@PathVariable("bookmarkId") Long bookmarkId){
        bookmarkServices.removeBookmark(bookmarkId);
        return ResponseEntity.ok().body("Bookmark removed successfully");
    }
}
