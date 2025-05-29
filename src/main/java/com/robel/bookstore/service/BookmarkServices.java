package com.robel.bookstore.service;

import com.robel.bookstore.dto.BookmarkCreateDTO;
import com.robel.bookstore.dto.BookmarkResponseDTO;
import com.robel.bookstore.dto.LikeCreateDTO;
import com.robel.bookstore.dto.LikeResponseDTO;

public interface BookmarkServices {

    BookmarkResponseDTO addBookmark(BookmarkCreateDTO bookmarkCreateDTO);
    void removeBookmark(Long bookmarkId);
}
