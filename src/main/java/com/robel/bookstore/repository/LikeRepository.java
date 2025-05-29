package com.robel.bookstore.repository;

import com.robel.bookstore.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUser_UserIdAndBook_BookId(Long userId, Long bookId);
}
