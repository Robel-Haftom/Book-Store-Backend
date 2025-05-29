package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.BookmarkCreateDTO;
import com.robel.bookstore.dto.BookmarkResponseDTO;
import com.robel.bookstore.entity.Bookmark;

public class BookmarkMapper {

    public static BookmarkResponseDTO mapToBookmarkResponseDTO(Bookmark bookmark){
        return new BookmarkResponseDTO(
                bookmark.getBookmarkId(),
                bookmark.getUser().getUserName(),
                bookmark.getBook().getBookTitle()
        );
    }
}
