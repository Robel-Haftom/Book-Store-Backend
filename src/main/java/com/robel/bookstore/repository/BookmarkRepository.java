package com.robel.bookstore.repository;

import com.robel.bookstore.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUser_UserIdAndBook_BookId(Long userId, Long bookId);
}
