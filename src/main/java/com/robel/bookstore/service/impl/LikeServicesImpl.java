package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.LikeCreateDTO;
import com.robel.bookstore.dto.LikeResponseDTO;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.entity.Like;
import com.robel.bookstore.entity.User;
import com.robel.bookstore.exception.BookNotFoundException;
import com.robel.bookstore.exception.LikeExistException;
import com.robel.bookstore.exception.LikeNotFoundException;
import com.robel.bookstore.exception.UserNotFoundException;
import com.robel.bookstore.mapper.LikeMapper;
import com.robel.bookstore.repository.BookRepository;
import com.robel.bookstore.repository.LikeRepository;
import com.robel.bookstore.repository.UserRepository;
import com.robel.bookstore.service.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServicesImpl implements LikeServices {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public LikeResponseDTO likeBook(LikeCreateDTO likeCreateDTO) {
        User user = userRepository.findById(likeCreateDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No user found with this id: " + likeCreateDTO.getUserId()));
        Book book = bookRepository.findById(likeCreateDTO.getBookId())
                .orElseThrow(() -> new BookNotFoundException("No book found with this id: " + likeCreateDTO.getBookId()));
        likeRepository.findByUser_UserIdAndBook_BookId(likeCreateDTO.getUserId(), likeCreateDTO.getBookId())
                .ifPresent((like) ->{
                    throw new LikeExistException("You already like this book");
                });
        Like like = Like.builder()
                .book(book)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        Like savedLike = likeRepository.save(like);

        return LikeMapper.mapToLikeResponseDTO(savedLike);
    }

    @Override
    public void removeLike(Long likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new LikeNotFoundException("No like found with this id: " + likeId));
        likeRepository.deleteById(likeId);
    }
}
