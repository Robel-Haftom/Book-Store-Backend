package com.robel.bookstore.service;

import com.robel.bookstore.dto.LikeCreateDTO;
import com.robel.bookstore.dto.LikeResponseDTO;

public interface LikeServices {

    LikeResponseDTO likeBook(LikeCreateDTO likeCreateDTO);
    void removeLike(Long likeId);
}
