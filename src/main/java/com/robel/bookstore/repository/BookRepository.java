package com.robel.bookstore.repository;

import com.robel.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookTitleAndBookAuthorAndBookPublisher(String bookTitle, String bookAuthor, String bookPublisher);
}
