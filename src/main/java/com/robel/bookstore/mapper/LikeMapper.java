package com.robel.bookstore.mapper;

import com.robel.bookstore.dto.LikeResponseDTO;
import com.robel.bookstore.entity.Like;

public class LikeMapper {
    public static LikeResponseDTO mapToLikeResponseDTO(Like like){
        return new LikeResponseDTO(
                like.getLikeId(),
                like.getUser().getUserName(),
                like.getBook().getBookTitle(),
                like.getCreatedAt().toString()
        );
    }
}
